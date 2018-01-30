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
        String s= "src/main/webapp/WEB-INF/download/centertemplate.csv";
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
           csvReader.setSkipEmptyRecords(true);
           csvReader.readHeaders();

                while (csvReader.readRecord()) {
                    CenterNode registerInfo = new CenterNode();
                    System.out.println("123"+csvReader.get("具体地址"));
                    registerInfo.setCAddress(csvReader.get("具体地址"));
                    registerInfo.setCName(csvReader.get("地点名字"));
                    registerInfo.setCQuatity(Double.valueOf(csvReader.get("存储量（元）")));
                    registerInfo.setCType(Integer.valueOf(csvReader.get("中心点编号")));
                    System.out.println(registerInfo.toString());
                }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}