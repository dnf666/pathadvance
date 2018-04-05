package dlf;

import apple.laf.JRSUIUtils;
import com.path.controller.distance.DistanceController;
import com.path.dao.*;
import com.path.model.*;
import com.path.service.centernode.CenterNodeService;
import com.sdicons.json.validator.impl.predicates.Array;
import com.sdicons.json.validator.impl.predicates.Int;
import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;
import javax.xml.ws.Service;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * @author demo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:springconfig.xml")
public class RouteControllerTest {
    @Resource
    private RouteMapper routeMapper;
    @Resource
    private CenterNodeMapper centerNodeMapper;
    @Resource
    private ServiceNodeMapper serviceNodeMapper;
    @Resource
    private DistanceMapper distanceMapper;
    @Resource
    private FinalSolutionMapper finalSolutionMapper;

    @Test
    public void genetic() {
        //取出所有服务点和中心点
        List<CenterNode> centerNodeList = centerNodeMapper.selectAllCenterNodeAddress("1");
        List<ServiceNode> serviceNodeList = serviceNodeMapper.selectAllServiceNodeAddress("1");
        int count = 10;
        int minDistance = 0;
        int i = 0;
        List<RouteTemp> routeList = initializeRoute(count, centerNodeList, serviceNodeList);
        if (!routeList.isEmpty()) {
            TreeMap<Integer,RouteTemp> treeMap = saveDistanceAndRoute(routeList);
            minDistance = (int) treeMap.firstKey();
            //杂交变异
            while (i < 10) {
                treeMap = hybridMutation(treeMap);
                int min = (int) treeMap.firstKey();
                if(minDistance == min){
                    i++;
                }else{
                    minDistance = min;
                    i = 0;
                }
            }
            //已经得到了比较好的路线方案，需要改变格式保存
            List<Route> list = new ArrayList<>();
            Set<Map.Entry<Integer,RouteTemp>> set = treeMap.entrySet();
            Iterator iterator = set.iterator();
            List<RouteTemp> list1 = new ArrayList<>();
            while(iterator.hasNext()) {
                RouteTemp routeTemp = ((Map.Entry<Integer,RouteTemp>)(iterator.next())).getValue();
                list1.add(routeTemp);
            }
            for (int j = 0; j < list1.size(); j++) {
                List<List> nodes = list1.get(j).getRoute();
                FinalSolution finalSolution = new FinalSolution();
                finalSolution.setQId(1);
                finalSolution.setFId(j+1);
                finalSolutionMapper.insert(finalSolution);
                 list = madeRoute(nodes,j+1);
                for (int k = 0; k < list.size(); k++) {

                    int result = routeMapper.insert(list.get(k));

                }

            }

            //现在开始计算时间，并转化成route对象


        } else {
        }
    }

    /**
     * 构造字符串
     * @param nodes 地点
     * @return 字符串
     */
    private List madeRoute(List<List> nodes ,int finalId) {
        List list = new ArrayList();
        StringBuilder s = new StringBuilder();
        double dis = 0;
        double time = 0;
        //路线数
        for (int i = 0; i < nodes.size(); i++) {
            List node = nodes.get(i);
            //每条路线节点数
            for (int k = 0; k < node.size(); k++) {
                Object b = node.get(k);
                if (b instanceof CenterNode) {
                    CenterNode centerNode = (CenterNode) b;
                    s.append(centerNode.getCAddress() + ",");
                }
                if (b instanceof ServiceNode) {
                    ServiceNode serviceNode = (ServiceNode) b;
                    s.append(serviceNode.getSAddress() + ",");
                }
            }

            for (int k = 0; k < node.size() - 1; k++) {
                Object a = node.get(k);
                Object b = node.get(k + 1);
                DistanceKey distance = getDistanceKey(a, b);
                Distance distance1 = distanceMapper.selectByPrimaryKey(distance);
                dis = dis + distance1.getStandardDis();
                time = time + distance1.getStandardTime();
            }
            Route route = new Route();
            route.setTotalTime(time);
            route.setTotalDis(dis);
            route.setRoute(s.toString());
            route.setVId(101);
            route.setFId(finalId);
            list.add(route);
        }
        return list;
    }

    /**
     * 杂交和变异
     *
     * @param treeMap 保存有距离和路线的结构
     */
    private TreeMap<Integer,RouteTemp> hybridMutation(TreeMap<Integer,RouteTemp> treeMap) {
        //先杂交
        Map.Entry<Integer, RouteTemp> entry = treeMap.firstEntry();
        List<List> route =entry.getValue().getRoute();
        int routeCount = route.size();
        //直接变异
        if (routeCount == 1) {
            List<List> routeTemp = mutation(route);
            entry.getValue().setRoute(routeTemp);
        }else {
            //默认必须杂交
            route = hybrid(route, routeCount);
            //变异随机
            route = mutation(route);
            int distance = caculateDistance(route);
            int maxDistance = (int) treeMap.lastKey();
            if (!treeMap.containsKey(distance)) {
                if (distance < maxDistance) {
                    treeMap.pollLastEntry();
                    RouteTemp routeTemp = new RouteTemp(route);
                    treeMap.put(distance, routeTemp);
                }
            }
        }
       return treeMap;
    }

    /**
     * 计算距离
     * @param route 需要计算的路径
     * @return
     */
    private int caculateDistance(List<List> route) {
        int size = route.size();
        int totalDis = 0;
        for (int i = 0; i < size; i++) {
            List nodes = route.get(i);
            int nodeSize = nodes.size();
            for (int j = 0; j < nodeSize-1; j++) {
                Object a = nodes.get(j);
                Object b = nodes.get(j + 1);
                DistanceKey distance = getDistanceKey(a,b);
                Distance distance1 = distanceMapper.selectByPrimaryKey(distance);
                totalDis = totalDis + distance1.getStandardDis();
            }
        }
        return totalDis;
    }

    /**
     * 取得distanceKeY,方便查询
     * @param a 起点对象
     * @param b 终点对象
     * @return 用于查询的对象
     */
    private DistanceKey getDistanceKey(Object a,Object b) {
        DistanceKey distance = new DistanceKey();
        if (a instanceof CenterNode) {
            distance.setStartId(((CenterNode) a).getCNum());
        }
        if (a instanceof ServiceNode) {
            distance.setStartId(((ServiceNode) a).getSNum());
        }
        if (b instanceof CenterNode) {
            distance.setEndId(((CenterNode) b).getCNum());
        }
        if (b instanceof ServiceNode) {
            distance.setEndId(((ServiceNode) b).getSNum());
        }
        return distance;
    }


    /**
     * 杂交
     * @param route 需要杂交的路径
     * @param routeCount 路径的数量
     * @return 杂交后的路径
     */
    private List<List> hybrid(List<List> route, int routeCount) {
        int route1 = (int) (Math.random() * routeCount);
        int route2 = (int) (Math.random() * routeCount);
        while (route1 == route2) {
            route2 = (int) (Math.random() * routeCount);
        }
        List routeWay1 = route.get(route1);
        List routeWay2 = route.get(route2);
        int routeWaySize1 = routeWay1.size();
        int routeWaySize2 = routeWay2.size();
        int routeIndex1 = (int) (Math.random() * routeWaySize1);
        int routeIndex2 = (int) (Math.random() * routeWaySize2);
        //防止取到首和尾
        routeIndex1 = notInHeadAndTail(routeWaySize1, routeIndex1);
        routeIndex2 = notInHeadAndTail(routeWaySize2, routeIndex2);
        int indexCount1 = (int) (Math.random() * (routeWaySize1 - routeIndex1));
        int indexCount2 = (int) (Math.random() * (routeWaySize2 - routeIndex2));
            indexCount1 = confirmNotZero(indexCount1,routeWaySize1 - routeIndex1);
            indexCount2 = confirmNotZero(indexCount2,routeWaySize2 - routeIndex2);
        //将数组拆成三段,每段都添加
        List head1 = new ArrayList(routeWay1.subList(0, routeIndex1));
        List middle1 = new ArrayList(routeWay1.subList(routeIndex1, routeIndex1 + indexCount1));
        List tail1 = new ArrayList(routeWay1.subList(routeIndex1 + indexCount1, routeWaySize1));
        List head2 = new ArrayList(routeWay2.subList(0, routeIndex2));
        List middle2 = new ArrayList(routeWay2.subList(routeIndex2, routeIndex2 + indexCount2));
        List tail2 = new ArrayList(routeWay2.subList(routeIndex2 + indexCount2, routeWaySize2));
        head1.addAll(middle2);
        head1.addAll(tail1);
        head2.addAll(middle1);
        head2.addAll(tail2);
        route.set(route1,head1);
        route.set(route2,head2);
        return route;
    }

    /**
     * 组成新路径
     * @param head1 头部
     * @param middle1 中部
     * @param tail1 尾部
     * @return 路径
     */
    private List newWay(List head1, List middle1, List tail1) {
       Iterator iterator = middle1.iterator();
       while (iterator.hasNext())
       {
           head1.add(iterator.next());
       }
       Iterator iterator1 = tail1.iterator();
       while (iterator1.hasNext())
       {
           head1.add(iterator1.next());
       }
       return head1;
    }

    /**
     * 防止取到中心点
     * @param routeWaySize1 路线程度
     * @param routeIndex1 杂交或变异的位置
     * @return 非中心点的位置
     */
    private int notInHeadAndTail(int routeWaySize1, int routeIndex1) {

        while (routeIndex1 == 0 || routeIndex1 == routeWaySize1-1) {
       routeIndex1 = (int) (Math.random() * routeWaySize1);
        }
        return routeIndex1;
    }

    /**
     * 变异
     * @param route 需要变异的路径
     * @return 变异后的路径
     */
    private List<List> mutation(List<List> route) {
        int routeSize = route.size();
        int route1 = (int) (Math.random() * routeSize);
        List routeWay1 = route.get(route1);
        int size = routeWay1.size();
        int node1 = 0;
        int node2 = 0;
        node1 = notInHeadAndTail(size,node1);
        node2 = notInHeadAndTail(size,node2);
        if (node1 == node2){
            return route;
        }
        else{
            ServiceNode serviceNode = (ServiceNode) routeWay1.get(node1);
            routeWay1.set(node1,routeWay1.get(node2));
            routeWay1.set(node2,serviceNode);
            return route;
        }
    }


    /**
     * 存储距离和方案到treemap中
     *
     * @param routeList 随机得到的路线
     * @return treemap
     */
    private TreeMap<Integer,RouteTemp> saveDistanceAndRoute(List<RouteTemp> routeList) {
        TreeMap<Integer, RouteTemp> map = new TreeMap<>();
        DistanceKey distance = null;
        List node = null;
        int totalDis = 0;
        int routeSize = routeList.size();
        for (int i = 0; i < routeSize; i++) {
            RouteTemp routeTemp = routeList.get(i);
            List<List> route = routeTemp.getRoute();
            int routeCount = route.size();
            for (int j = 0; j < routeCount; j++) {
                int size = route.get(j).size();
                node = route.get(j);
                for (int k = 0; k < size - 1; k++) {
                    Object a = node.get(k);
                    Object b = node.get(k + 1);
                   distance = getDistanceKey(a,b);
                    Distance distance1 = distanceMapper.selectByPrimaryKey(distance);
                    totalDis = totalDis + distance1.getStandardDis();

                }
            }
            map.put(totalDis, routeTemp);
        }
        return map;
    }

    /**
     * 初始化路线
     *
     * @param count           路线数量
     * @param serviceNodeList 服务点列表
     * @return
     */
    private List<RouteTemp> initializeRoute(int count, List<CenterNode> centerNodeList, List<ServiceNode> serviceNodeList) {
        CenterNode centerNode = centerNodeList.get(0);
        List<RouteTemp> routeList = new ArrayList<>();
        int routeRandom =4;
        RouteTemp oneRoute = null;
        //随机产生路径
        for (int i = 0; i < count; i++) {
            List<ServiceNode> list2 = new ArrayList<>(serviceNodeList);
            //确定这个方案生成几条路径，最多3条
            int routeCount = (int) (Math.random() * routeRandom);
            //防止route的数量为0
            routeCount = confirmNotZero(routeCount,routeRandom);
            oneRoute = new RouteTemp(routeCount);
            //确定每条route的数量
            for (int j = 0; j < routeCount; j++) {
                if (list2.isEmpty()){
                    break;
                }
                List everyPath = oneRoute.getRoute().get(j);
                //开头用中心点
                everyPath.add(centerNode);
                int listSize = list2.size();
                //还剩最后一条路径时，直接生成。防止点取不完
                if (j == routeCount - 1) {
                    list2.stream().forEach(e -> everyPath.add(e));
                    everyPath.add(centerNode);
                    break;
                }
                //如果服务点还剩一个，直接加入并退出
                if (listSize == 1) {
                    everyPath.add(list2.get(0));
                    everyPath.add(centerNode);
                    break;
                }
                //防止路径node数为0
                int everyRouteNode = (int) (Math.random() * listSize);
                everyRouteNode = confirmNotZero(everyRouteNode,listSize);
                //开始取点
                for (int k = 0; k < everyRouteNode; k++) {
                    //随机取某一个点
                    int index = (int) (Math.random() * list2.size());
                    ServiceNode serviceNode = list2.get(index);
                    //路线中加点，服务点少一个点
                    oneRoute.getRoute().get(j).add(serviceNode);
                    list2.remove(index);
                }
                //结尾用中心点结尾
                oneRoute.getRoute().get(j).add(centerNode);

            }
            //防止空路径产生
            for (int j = 0; j < routeCount; j++) {
                List list = oneRoute.getRoute().get(j);
                if (list.isEmpty()){
                    oneRoute.getRoute().remove(j);
                }
            }
            routeList.add(oneRoute);

        }
        return routeList;
    }

    /**
     * 确保不会取到0
     * @param routeCount 路线数量
     * @param routeRamdom 路线最大限制
     * @return 非0整数
     */
    private int confirmNotZero(int routeCount,int routeRamdom) {
        while (routeCount == 0) {
            routeCount = (int) (Math.random() * routeRamdom);
        }
        return routeCount;
    }

    @Test
    public void genetic1() {
        double x = (Math.random());
        int y = (int) x;
        System.out.println(x);
        System.out.println(y);
        List<ServiceNode> list1 = serviceNodeMapper.selectAllServiceNodeAddress("1");
        List<ServiceNode> list2 = new ArrayList<>(list1);
        System.out.println(list1);
        list1.remove(1);
        System.out.println(list2);
    }

    @Test
    public void genetic2() {
//        List<ServiceNode> list1 = serviceNodeMapper.selectAllServiceNodeAddress("1");
//        List<String> names =list1.stream().map(ServiceNode::getSName).collect(Collectors.toList());
//        System.out.println(names);
//        DistanceKey distance = new Distance();
//        distance.setDId(1);
//        distance.setStartId("c1");
//        distance.setEndId("s1009");
//        System.out.println(distanceMapper.selectByPrimaryKey(distance));
        Distance distance = new Distance();
        List list = new ArrayList();
        List list1 = new ArrayList();
        list.add("1");
        list.add("2");
        List list2 = list.subList(0,0);
        list.addAll(list2);
        list1.add("3");
        list1.add("4");
        String s = (String) list1.get(1);
        list1.set(1, list.get(1));
        list.set(1, s);

        System.out.println(list.toString());
        System.out.println(list1);

    }
    @Test
    public void delete (){
        routeMapper.deleteAll(1);
    }
    @Test
    public void delete1 () {
        TreeMap<Integer, String> s = new TreeMap<>();
        s.put(1, "1");
        s.put(2, "2");
        s.put(3, "3");
        List<Integer> list = new ArrayList<>();
        SortedMap<Integer, String> sortedMap = s.subMap(0, 1);
        System.out.println(s.ceilingKey(0));
        int size = s.size();
        for (int i = 1; i <= size; i++) {
            for (int j = i; j <= size; j++) {
                list.add(i);
            }
        }
        System.out.println(list);
        int a = (int) (Math.random() * list.size());
        int a1 = list.get(a);
        Set<Integer> entry = s.keySet();
        Iterator iterator = entry.iterator();
        for (int i = 0; i < a1; i++) {
            a = (int) iterator.next();
        }
        System.out.println(a);
    }
}
