package com.path.service.csv.impl;

import com.csvreader.CsvReader;
import com.mchange.v2.util.PropertiesUtils;
import com.path.dao.CenterNodeMapper;
import com.path.model.CenterNode;
import com.path.model.Path;
import com.path.service.csv.CsvService;
import com.path.util.PropertiesUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author demo
 */
@Service
public class CenterCsvServiceImpl implements CsvService<CenterNode> {
    @Resource
    private CenterNodeMapper centerNodeMapper;

    @Override
    public List<CenterNode> readCsv(Path path) throws IOException {
        path.setQuestionId(1);
        String centernode = "centernode.properties";
        List<CenterNode> list = new ArrayList<CenterNode>();
        char delimiter = ',';
        CsvReader csvReader = new CsvReader(path.getPath(), delimiter, Charset.forName("gbk"));
        csvReader.setSkipEmptyRecords(true);
        csvReader.readHeaders();
      Properties properties =  PropertiesUtil.propertitesUtil(path.getProjectPath(),centernode);
      String bianhao = properties.getProperty("bianhao");
      String mingcheng = properties.getProperty("mingcheng");
      String dizhi = properties.getProperty("dizhi");
      String leixing = properties.getProperty("leixing");
      String cunchu = properties.getProperty("cunchu");
        while(csvReader.readRecord())
        {
            CenterNode centerNode = new CenterNode();
            centerNode.setCId(path.getQuestionId());
            centerNode.setCNum(csvReader.get(bianhao));
            centerNode.setCName(csvReader.get(mingcheng));
            centerNode.setCAddress(csvReader.get(dizhi));
            centerNode.setCType(Integer.valueOf(csvReader.get(leixing)));
            centerNode.setCQuatity(Double.valueOf(csvReader.get(cunchu)));
            list.add(centerNode);
        }
        return list;


    }

    @Override
    public List<CenterNode> removeDuplication(List<CenterNode> list) throws Exception {
        int i=1;
        //删除不完整数据
        List<CenterNode> list1 = list.stream().filter((e)->e.getCNum()!=null||!("".equals(e.getCNum()))).collect(Collectors.toList());
        List<CenterNode> list2 = list1.stream().filter((e)->e.getCName()!=null||!("".equals(e.getCName()))).collect(Collectors.toList());
        List<CenterNode> list3 = list2.stream().filter((e)->e.getCAddress()!=null||!("".equals(e.getCAddress()))).collect(Collectors.toList());
        List<CenterNode> list4 = list3.stream().filter((e)->e.getCType()!=null||!("".equals(e.getCType()))).collect(Collectors.toList());
        List<CenterNode> list5 = list4.stream().filter((e)->e.getCQuatity()!=null||!("".equals(e.getCQuatity()))).collect(Collectors.toList());
        //去除重复数据
        Iterator<CenterNode> iterator = list5.iterator();
        Set<String> set = new HashSet<String>();
        while (iterator.hasNext()) {
            String num = iterator.next().getCNum();
            if (set.contains(num)) {
                throw new Exception("第"+i+"行与其他数据有冲突");
            }
            else {
                i++;
                set.add(num);
            }

        }
        return list5;
    }

    @Override
    public boolean storeDatabase(List<CenterNode> list) {
        boolean result = centerNodeMapper.insertAdvance(list);
        return result;
    }


}
