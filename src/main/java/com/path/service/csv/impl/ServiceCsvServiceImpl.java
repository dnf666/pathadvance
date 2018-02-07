package com.path.service.csv.impl;

import com.csvreader.CsvReader;
import com.path.dao.ServiceNodeMapper;
import com.path.model.ServiceNode;
import com.path.model.Path;
import com.path.model.ServiceNode;
import com.path.service.csv.CsvService;
import com.path.util.PropertiesUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author demo
 */
@Service
public class ServiceCsvServiceImpl implements CsvService<ServiceNode>{
    @Resource
    private ServiceNodeMapper serviceNodeMapper;


    @Override
    public List<ServiceNode> readCsv(Path path) throws IOException {
        String servicenode = "servicenode.properties";
        List<ServiceNode> list = new ArrayList<ServiceNode>();
        char delimiter = ',';
        CsvReader csvReader = new CsvReader(path.getPath(), delimiter, Charset.forName("gbk"));
        csvReader.setSkipEmptyRecords(true);
        csvReader.readHeaders();
        Properties properties =  PropertiesUtil.propertitesUtil(path.getProjectPath(),servicenode);
        String bianhao = properties.getProperty("bianhao");
        String mingcheng = properties.getProperty("mingcheng");
        String dizhi = properties.getProperty("dizhi");
        String leixing = properties.getProperty("leixing");
        String cunchu = properties.getProperty("cunchu");

        while(csvReader.readRecord())
        {
            ServiceNode serviceNode = new ServiceNode();
            serviceNode.setSId(path.getQuestionId());
            serviceNode.setSNum(csvReader.get(bianhao));
            serviceNode.setSName(csvReader.get(mingcheng));
            serviceNode.setSAddress(csvReader.get(dizhi));
            serviceNode.setSType(Integer.valueOf(csvReader.get(leixing)));
            serviceNode.setSQuatity(Double.valueOf(csvReader.get(cunchu)));
            list.add(serviceNode);
        }
        return list;
    }

    @Override
   public List<ServiceNode> checkFile(List<ServiceNode> list) throws Exception {
        int i = 1;
        String empty = "";
        Map<String,Integer> map = new HashMap<>();
        //检查不完整数据
        Iterator<ServiceNode> iterator = list.iterator();
        while (iterator.hasNext()) {
            ServiceNode ServiceNode = iterator.next();
            if (ServiceNode.getSNum() == null || ServiceNode.getSNum().equals(empty)) {
                throw new Exception("第" + i + "行没有编号");
            }
            if (ServiceNode.getSName() == null || ServiceNode.getSName().equals(empty)) {
                throw new Exception("第" + i + "行没有名称");
            }
            if (ServiceNode.getSAddress() == null || ServiceNode.getSAddress().equals(empty)) {
                throw new Exception("第" + i + "行没有地址");
            }
            if (ServiceNode.getSType().toString() == null || ServiceNode.getSType().toString().equals(empty)) {
                throw new Exception("第" + i + "行没有类型");
            }
             if(ServiceNode.getSQuatity().toString()==null || ServiceNode.getSQuatity().toString().equals(empty))
            {
                throw new Exception("第"+i+"行没有存储量");
            }
            String cNum = ServiceNode.getSNum();
            if(map.containsKey(cNum)){
               int j =  map.get(cNum);
               throw  new Exception("第"+i+"行和第"+j+"行编号重复");
            }
            map.put(ServiceNode.getSNum(),i);

        }
        return list;
   }

    @Override
    public boolean storeDatabase(List<ServiceNode> list) {
        boolean result = serviceNodeMapper.insertAdvance(list);
        return result;
    }
}
