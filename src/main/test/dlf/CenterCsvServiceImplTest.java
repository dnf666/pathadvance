package dlf;

import com.csvreader.CsvReader;
import com.path.model.CenterNode;
import com.path.service.CenterNodeService;
import com.path.service.csv.CenterCsvService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author demo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:springconfig.xml")
public class CenterCsvServiceImplTest {
    @Resource
    private CenterCsvService centerCsvService;
    @Test
    public void ensureType() {
        String path = "/Users/demo/IdeaProjects/path/src/main/resources";
        File file = new File("/Users/demo/IdeaProjects/path/doc/项目总结.md");
        try {
            boolean i = centerCsvService.ensureType(file,path);
            System.out.println(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readCsv() throws IOException {
        String path = "/Users/demo/IdeaProjects/path/src/main/webapp/WEB-INF/download/中心点模板.csv";

        List<CenterNode> list = new ArrayList<CenterNode>();
        char delimiter = ',';
        CsvReader csvReader = new CsvReader(path, delimiter, Charset.forName("utf-8"));
        csvReader.setSkipEmptyRecords(true);
        csvReader.readHeaders();
        System.out.println(csvReader.getRawRecord());
        while(csvReader.readRecord())
        {
            CenterNode centerNode = new CenterNode();
            centerNode.setcNum(csvReader.get("中心点编号"));
            centerNode.setCName(csvReader.get("地点名字"));
            centerNode.setCAddress(csvReader.get("具体地址"));
            centerNode.setCType(Integer.valueOf(csvReader.get("类型")));
//            BigDecimal bigDecimal = new BigDecimal(csvReader.get("存储量（元）"));
//            System.out.println(bigDecimal.toPlainString());
            centerNode.setCQuatity(Double.parseDouble(csvReader.get("存储量（元）")));
            list.add(centerNode);
        }
        System.out.println(list);
    }

    @Test
    public void removeDuplication() {
    }

    @Test
    public void storeDatabase() {

    }
}