package dlf;

import com.csvreader.CsvReader;
import com.path.model.CenterNode;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

import static org.junit.Assert.*;

/**
 * @author demo
 */
public class CenterCsvControllerTest {

    @Test
    public void testCsv(){
        String s= "/Users/demo/IdeaProjects/path/src/main/webapp/WEB-INF/download/中心点.csv";
        File file = new File(s);
//        if(!file.exists())
//        {
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
       String path = file.getAbsolutePath();
        System.out.println(path);
       char delimiter = ',';
        try {
            CsvReader csvReader = new CsvReader(path,delimiter, Charset.forName("GBK"));
           csvReader.setSkipEmptyRecords(false);
           csvReader.readHeaders();
            System.out.println(csvReader.getValues());
            System.out.println(csvReader.getSkipEmptyRecords());
            System.out.println(csvReader.getSafetySwitch());
                while (csvReader.readRecord()) {
                    CenterNode registerInfo = new CenterNode();
                    registerInfo.setCAddress(csvReader.get("具体地址"));

                    registerInfo.setCName(csvReader.get("地点名称"));
                    registerInfo.setCQuatity(Double.valueOf(csvReader.get("存储量（元）")));
                    registerInfo.setCNum(csvReader.get("中心编号"));
                    registerInfo.setCType(Integer.valueOf(csvReader.get("类型")));
                    System.out.println(registerInfo.toString());
                }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}