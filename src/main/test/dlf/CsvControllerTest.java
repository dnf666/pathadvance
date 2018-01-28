package dlf;

import com.csvreader.CsvReader;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

import static org.junit.Assert.*;

/**
 * @author demo
 */
public class CsvControllerTest {

    @Test
    public void testCsv(){
        String s= "/path/webapp/WEB-INF/download/centertemplate.xlsx";
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
       File file1 = new File(path);

        if(!file1.exists())
        {
            try {
                file1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(file.exists());
       char delimiter = ',';
        try {
            CsvReader csvReader = new CsvReader(path,delimiter, Charset.forName("utf-8"));
            System.out.println(csvReader.get("中心点编号"));
            System.out.println(csvReader.get("地点名字"));
            System.out.println(csvReader.get("具体地址"));
            System.out.println(csvReader.get("存储量（元）"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}