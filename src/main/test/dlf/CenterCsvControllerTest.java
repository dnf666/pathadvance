package dlf;

import com.csvreader.CsvReader;
import com.path.model.CenterNode;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Scanner;

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
    @Test
    public  void s(){
        int i[] = new int[10];
        for (int j = 0; j < i.length; j++) {
            i[j] = 0;
        }


        Scanner scanner = new Scanner(System.in);
       String s = scanner.next();
      char c[] = s.toCharArray();
      //存储每个可用的字符
        for (int j = 0; j < c.length; j++) {
            i[(int)c[j]]++;
        }

        boolean flag = false;
        for (int j = 0; j < Integer.MAX_VALUE; j++) {
            if (flag == false) {
                String s1 = "" + j;
                int i3[] = i;
                char[] c1 = s1.toCharArray();
                for (int k = 0; k < c1.length; k++) {
                    if (i3[(int) c1[k]] > 0) {
                        i3[(int) c1[k]]--;
                    } else {
                        System.out.println(j);
                        flag = true;
                        break;
                    }
                }
            }else{
                break;
            }
        }



    }

    public static void main(String[] args) {
        int i[] = new int[10];
        for (int j = 0; j < i.length; j++) {
            i[j] = 0;
        }


        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        Integer integer = Integer.parseInt(s);
        //存储每个可用的字符
        while (integer != 0) {
            i[integer % 10]++;
            integer = integer / 10;

        }
        boolean flag = false;
        for (int j = 0; j < Integer.MAX_VALUE; j++) {
            if (flag == false) {
                String s1 = "" + j;
                int i3[] = i;
                while (j != 0) {
                    if (i3[j % 10] > 0) {
                        i3[j % 10]--;
                        j = j / 10;
                    } else {
                        System.out.println(j);
                        flag = true;
                        break;
                    }
                }

            }


        }
    }}