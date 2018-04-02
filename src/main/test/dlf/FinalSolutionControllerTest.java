package dlf;

import com.path.model.FinalSolution;
import com.path.service.finalsolution.FinalSolutionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author demo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:springconfig.xml")
public class FinalSolutionControllerTest {
    @Resource
    private FinalSolutionService finalSolutionService;
    @Test
    public void addFinalSolution() {

        finalSolutionService.insert(new FinalSolution(1,1));
    }


}