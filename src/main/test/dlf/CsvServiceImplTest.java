package dlf;

import com.path.model.Vahicle;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
            in = new BufferedInputStream(new FileInputStream(new File("src/main/resources/properties/type.properties")));

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
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("springconfig.xml");

    }



    @Test
    public void storeDatabase() {
    }
}