package dlf;

import com.path.dao.RouteMapper;
import com.sdicons.json.validator.impl.predicates.Int;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author demo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:springconfig.xml")
public class RouteControllerTest {
    @Resource
    private RouteMapper routeMapper;
    @Test
    public void addRoute() {
        System.out.println( routeMapper.findRouteByFid(3));

        }
    }
