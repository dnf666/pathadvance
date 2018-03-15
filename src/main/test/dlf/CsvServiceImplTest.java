package dlf;

import com.path.model.Vahicle;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.sql.*;
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
        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver"); // 注册数据库驱动
            String url = "jdbc:mysql://localhost:3306/path"; // briup为数据库名称
            try {
                conn = DriverManager.getConnection(url, "root","root"); // 获取连接数据库的Connection对象
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Statement statement = conn.createStatement();
            ResultSet set = statement.executeQuery("select * from servicenode");
            while (set.next()){
                System.out.println(set.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    @Test
    public void storeDatabase() {
    }
}