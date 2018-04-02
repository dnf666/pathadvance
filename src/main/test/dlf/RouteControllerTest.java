package dlf;

import com.path.controller.distance.DistanceController;
import com.path.dao.CenterNodeMapper;
import com.path.dao.DistanceMapper;
import com.path.dao.RouteMapper;
import com.path.dao.ServiceNodeMapper;
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

    @Test
    public void addRoute() {
        System.out.println(routeMapper.findRouteByFid(3));
    }

    @Test
    public void genetic() {
        //取出所有服务点和中心点
        List<CenterNode> centerNodeList = centerNodeMapper.selectAllCenterNodeAddress("1");
        List<ServiceNode> serviceNodeList = serviceNodeMapper.selectAllServiceNodeAddress("1");
        int count = 10;
        List<RouteTemp> routeList = initializeRoute(count, centerNodeList, serviceNodeList);
        if (!routeList.isEmpty()) {
            Map treeMap = saveDistanceAndRoute(routeList);
            System.out.println(1);
        } else {
            System.out.println("没有路径");
        }
    }

    /**
     * 存储距离和方案到treemap中
     *
     * @param routeList
     * @return
     */
    private Map saveDistanceAndRoute(List<RouteTemp> routeList) {
        Map<Integer, List> map = new TreeMap<>();
        DistanceKey distance = new Distance();
        List node = null;
        int totalDis = 0;
        int routeSize = routeList.size();
        for (int i = 0; i < routeSize; i++) {
            List<List> route = routeList.get(i).getRoute();
            int routeCount = route.size();
            for (int j = 0; j < routeCount ; j++) {
                int size = route.get(j).size();
                node = route.get(j);
                for (int k = 0; k < size - 1; k++) {
                    Object a = node.get(k);
                    Object b = node.get(k + 1);
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
                    Distance distance1 = distanceMapper.selectByPrimaryKey(distance);
                    totalDis = totalDis + distance1.getStandardDis();
                }
                map.put(totalDis, route);

            }
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
        RouteTemp oneRoute = null;
        //随机产生路径
        for (int i = 0; i < count; i++) {
            List<ServiceNode> list2 = new ArrayList<>(serviceNodeList);
            //确定这个方案生成几条路径，最多3条
            int routeCount = (int) (Math.random() * 3);
            //防止route的数量为0
            routeCount = confirmNotZero(routeCount);
            oneRoute = new RouteTemp(routeCount);
            oneRoute.setId(i);
            //确定每条route的数量
            for (int j = 0; j < routeCount; j++) {
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

                int everyRouteNode = (int) (Math.random() * listSize);
                everyRouteNode = confirmNotZero(everyRouteNode);
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
            routeList.add(oneRoute);
        }
        return routeList;
    }

    private int confirmNotZero(int routeCount) {
        while (routeCount == 0) {
            routeCount = (int) (Math.random() * 3);
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
        list.add(distance);
        list.add(distance);
        System.out.println(list);

    }
}
