package com.path.service.csv.impl;

import com.csvreader.CsvReader;
import com.path.dao.ServiceNodeMapper;
import com.path.model.CenterNode;
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
    public List<ServiceNode> removeDuplication(List<ServiceNode> list) throws Exception {
        int i=1;
        //删除不完整数据
        List<ServiceNode> list1 = list.stream().filter((e)->e.getSNum()!=null||!("".equals(e.getSNum()))).collect(Collectors.toList());
        List<ServiceNode> list2 = list1.stream().filter((e)->e.getSName()!=null||!("".equals(e.getSName()))).collect(Collectors.toList());
        List<ServiceNode> list3 = list2.stream().filter((e)->e.getSAddress()!=null||!("".equals(e.getSAddress()))).collect(Collectors.toList());
        List<ServiceNode> list4 = list3.stream().filter((e)->e.getSType()!=null||!("".equals(e.getSType()))).collect(Collectors.toList());
        List<ServiceNode> list5 = list4.stream().filter((e)->e.getSQuatity()!=null||!("".equals(e.getSQuatity()))).collect(Collectors.toList());
        //去除重复数据
        Iterator<ServiceNode> iterator = list5.iterator();
        Set<String> set = new HashSet<String>();
        while (iterator.hasNext()) {
            String num = iterator.next().getSNum();
            if (set.contains(num)) {
                throw new Exception("第"+i+"行数据与其他数据有冲突");
            }
            else {
                i++;
                set.add(num);
            }

        }
        return list5;

    }

    @Override
    public boolean storeDatabase(List<ServiceNode> list) {
        boolean result = serviceNodeMapper.insertAdvance(list);
        return result;
    }
}
