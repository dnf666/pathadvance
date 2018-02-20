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
        String centernode = "centernode.properties";
        List<CenterNode> list = new ArrayList<CenterNode>();
        char delimiter = ',';
        CsvReader csvReader = new CsvReader(path.getPath(), delimiter, Charset.forName("gbk"));
        csvReader.setSkipEmptyRecords(true);
        csvReader.readHeaders();
        Properties properties = PropertiesUtil.propertitesUtil(path.getProjectPath(), centernode);
        String bianhao = properties.getProperty("bianhao");
        String mingcheng = properties.getProperty("mingcheng");
        String dizhi = properties.getProperty("dizhi");
        String leixing = properties.getProperty("leixing");
        String cunchu = properties.getProperty("cunchu");
        while (csvReader.readRecord()) {
            //解决csv文件空记录
            if(csvReader.get(leixing)=="")
            {
                continue;
            }
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
    public List<CenterNode> checkFile(List<CenterNode> list) throws Exception {
        int i = 1;
        String empty = "";
        Map<String,Integer> map = new HashMap<>();
        //检查不完整数据
        Iterator<CenterNode> iterator = list.iterator();
        while (iterator.hasNext()) {
            CenterNode centerNode = iterator.next();
            if (centerNode.getCNum() == null || centerNode.getCNum().equals(empty)) {
                throw new Exception("第" + i + "行没有编号");
            }
            if (centerNode.getCName() == null || centerNode.getCName().equals(empty)) {
                throw new Exception("第" + i + "行没有名称");
            }
            if (centerNode.getCAddress() == null || centerNode.getCAddress().equals(empty)) {
                throw new Exception("第" + i + "行没有地址");
            }
            if (centerNode.getCType().toString() == null || centerNode.getCType().toString().equals(empty)) {
                throw new Exception("第" + i + "行没有类型");
            }
             if(centerNode.getCQuatity().toString()==null || centerNode.getCQuatity().toString().equals(empty))
            {
                throw new Exception("第"+i+"行没有存储量");
            }
            String cNum = centerNode.getCNum();
            if(map.containsKey(cNum)){
               int j =  map.get(cNum);
               throw  new Exception("第"+i+"行和第"+j+"行编号重复");
            }
            map.put(centerNode.getCNum(),i);

        }
        return list;
   }

    @Override
    public boolean storeDatabase(List<CenterNode> list) {
        boolean result = centerNodeMapper.insertAdvance(list);
        return result;
    }


}
