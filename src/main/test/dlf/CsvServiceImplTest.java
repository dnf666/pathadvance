package dlf;

import com.path.model.Vahicle;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.sql.*;
import java.util.*;

import static jdk.nashorn.internal.objects.NativeRegExp.getLastInput;
import static jdk.nashorn.internal.objects.NativeRegExp.test;
import static jdk.nashorn.internal.runtime.ScriptObject.getCount;
import static jdk.nashorn.internal.runtime.ScriptObject.setGlobalObjectProto;
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
        //输入完成
        Scanner scanner = new Scanner(System.in);
       int account =  scanner.nextInt();
       int cha = scanner.nextInt();
       int number[] = new int[account];
        for (int i = 0; i < account; i++) {
            number[i] = scanner.nextInt();
        }
        System.out.println(account);
        System.out.println(cha);
        System.out.println(number);
    }
static int cha = 3;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int account =  scanner.nextInt();
        int cha = scanner.nextInt();
        int count = 0;
        if (cha == 0){
            Map map = new HashMap();
            for (int i = 0; i < account; i++) {
                int a = scanner.nextInt();
                if (map.containsKey(a)) {
                    map.put(a,(int)map.get(a)+1);
                    if((int)map.get(a)<3){
                        count++;
                    }
                }else
                {
                 map.put(a,1);
                }
            }
        }else {
            List<Integer> number = new ArrayList<>(account);
            for (int i = 0; i < account; i++) {
                int a = scanner.nextInt();
                if (!number.contains(a)) {
                    number.add(a);
                }


            }
            for (int i = 0; i < number.size() - 1; i++) {
                for (int j = i + 1; j < number.size(); j++) {
                    if (number.get(i) - number.get(j) == cha || number.get(j) - number.get(i) == cha) {
                        count++;
                    }
                }
            }
        }
            System.out.println(count);

    }



    private static void test1(int i, int i1) {
        if (i-i1 == cha){

        }
    }
}