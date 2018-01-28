package dlf;

import org.junit.Test;

import java.io.*;
import java.util.Properties;

import static org.junit.Assert.*;

/**
 * @author demo
 */
public class CsvServiceImplTest {

    @Test
    public void ensureType() {
        String s = "车辆模板.xlsx";
        Properties properties = new Properties();
        InputStream in = null ;
        int mine =s.lastIndexOf(".");
        System.out.println(s.substring(mine+1));
        try{
            in = new BufferedInputStream(new FileInputStream(new File("/Users/demo/IdeaProjects/path/src/main/resources/properties/type.properties")));

            properties.load(in);
            String type1 = properties.getProperty("type");
            String type[] = type1.split(",");
            System.out.println(type1);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void readCsv() {
    }

    @Test
    public void removeDuplication() {
    }

    @Test
    public void storeDatabase() {
    }
}