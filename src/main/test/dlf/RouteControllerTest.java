package dlf;

import com.path.controller.distance.DistanceController;
import com.path.dao.CenterNodeMapper;
import com.path.dao.DistanceMapper;
import com.path.dao.RouteMapper;
import com.path.dao.ServiceNodeMapper;
import com.path.model.CenterNode;
import com.path.model.Distance;
import com.path.model.DistanceKey;
import com.path.model.ServiceNode;
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
        List<List> routeList = initializeRoute(count,centerNodeList,serviceNodeList);
        Map treeMap = saveDistanceAndRoute(routeList);
    }

    /**
     * 存储距离和方案到treemap中
     * @param routeList
     * @return
     */
    private Map saveDistanceAndRoute(List<List> routeList) {
        DistanceKey distance = new DistanceKey();
        int totalDis = 0;
        int routeSize = routeList.size();
        for (int i = 0; i < routeSize; i++) {
               List route =  routeList.get(i);
               int routeNodeSize = route.size();
            for (int j = 0; j < routeNodeSize-1; j++) {
                    Object a = route.get(j);
                    Object b = route.get(j+1);
                if (a instanceof CenterNode){
                    distance.setStartId (((CenterNode)a).getCNum());
                }
                if (a instanceof ServiceNode){
                    distance.setStartId (((ServiceNode)a).getSNum());
                }
                if (b instanceof CenterNode){
                    distance.setEndId (((CenterNode)b).getCNum());
                }
                if (b instanceof ServiceNode){
                    distance.setEndId (((ServiceNode)b).getSNum());
                }

            }
        }
        return null;
    }

    /**
     * 初始化路线
     * @param count 路线数量
     * @param serviceNodeList 服务点列表
     * @return
     */
    private List<List> initializeRoute(int count,List<CenterNode> centerNodeList, List<ServiceNode> serviceNodeList) {
        CenterNode centerNode = centerNodeList.get(0);
        List<List> routeList = new ArrayList<>();
        List oneRoute = null;
        //随机产生路径
        for (int i = 0; i < count; i++) {
            List<ServiceNode> list2 = new ArrayList<>(serviceNodeList);
            //确定这个方案生成几条路径，最多3条
            int routeCount = (int) (Math.random() * 3);
            //确定每条route的数量
            for (int j = 0; j < routeCount; j++) {
                oneRoute = new ArrayList<>();
                //开头用中心点
                oneRoute.add(centerNode);
                int listSize = list2.size();
                //如果服务点还剩一个，直接加入并退出
                if (listSize == 1) {
                    oneRoute.add(list2.get(1));
                    break;
                }
                int everyRouteNode = (int) (Math.random() * listSize);
                //开始取点
                for (int k = 0; k < everyRouteNode; k++) {
                    //随机取某一个点
                    int index = (int) (Math.random() * list2.size());
                    ServiceNode serviceNode = list2.get(index);
                    //路线中加点，服务点少一个点
                    oneRoute.add(serviceNode);
                    list2.remove(index);
                }
                //结尾用中心点结尾
                oneRoute.add(centerNode);

            }
            routeList.add(oneRoute);
        }
        return routeList;
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
        List<ServiceNode> list1 = serviceNodeMapper.selectAllServiceNodeAddress("1");
        List<String> names =list1.stream().map(ServiceNode::getSName).collect(Collectors.toList());
        System.out.println(names);
        DistanceKey distance = new Distance();
        distance.setDId(1);
        distance.setStartId("c1");
        distance.setEndId("s1009");
        System.out.println(distanceMapper.selectByPrimaryKey(distance));
    }
}
