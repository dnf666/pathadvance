//package dlf;
//
//import com.path.model.ServiceNode;
//import com.path.model.ServiceNode;
//import com.path.service.csv.CsvService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import javax.annotation.Resource;
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//
///**
// * @author demo
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:springconfig.xml")
//public class CenterCsvServiceImplTest {
//    @Resource
//    private CsvService<ServiceNode> csvService;
//    @Test
//    public void ensureType() {
//        String path = "/Users/demo/IdeaProjects/path/src/main/resources";
//        File file = new File("/Users/demo/IdeaProjects/path/doc/项目总结.md");
//        try {
//            boolean i = csvService.ensureType(file,path);
//            System.out.println(i);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void readCsv() throws IOException {
//        String path = "/Users/demo/IdeaProjects/path/src/main/webapp/WEB-INF/download/服务点.csv";
//File file = new File(path);
//Integer  i = 1;
////List<ServiceNode> list = csvService.readCsv();
//        List<ServiceNode> ServiceNodeList = null;
//        try {
//            ServiceNodeList = csvService.removeDuplication(list);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(ServiceNodeList);
//        csvService.storeDatabase(ServiceNodeList);
////        List<ServiceNode> list = new ArrayList<ServiceNode>();
////        char delimiter = ',';
////        CsvReader csvReader = new CsvReader(path, delimiter, Charset.forName("utf-8"));
////        csvReader.setSkipEmptyRecords(true);
////        csvReader.readHeaders();
////        System.out.println(csvReader.getRawRecord());
////        while(csvReader.readRecord())
////        {
////            ServiceNode ServiceNode = new ServiceNode();
////            ServiceNode.setcNum(csvReader.get("中心点编号"));
////            ServiceNode.setCName(csvReader.get("地点名字"));
////            ServiceNode.setCAddress(csvReader.get("具体地址"));
////            ServiceNode.setCType(Integer.valueOf(csvReader.get("类型")));
//////            BigDecimal bigDecimal = new BigDecimal(csvReader.get("存储量（元）"));
//////            System.out.println(bigDecimal.toPlainString());
////            ServiceNode.setCQuatity(Double.parseDouble(csvReader.get("存储量（元）")));
////            list.add(ServiceNode);
////        }
////        System.out.println(list);
//    }
//
//    @Test
//    public void removeDuplication() {
//    }
//
//    @Test
//    public void storeDatabase() {
//
//    }
//}