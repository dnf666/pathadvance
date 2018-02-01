package com.path.service.csv.impl;

import com.csvreader.CsvReader;
import com.path.dao.CenterNodeMapper;
import com.path.model.CenterNode;
import com.path.service.csv.CenterCsvService;
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
public class CenterCsvServiceImpl implements CenterCsvService {
    @Resource
    private CenterNodeMapper centerNodeMapper;

    @Override
    public boolean ensureType(File file,String path) throws Exception {
        String name = file.getName();
        int mine = name.lastIndexOf(".");
        String type = name.substring(mine + 1);
        Properties properties = new Properties();
        InputStream in = null;
        in = new BufferedInputStream(new FileInputStream(new File(path+"/properties/type.properties")));
        properties.load(in);
        String type1 = properties.getProperty("type");
        String types[] = type1.split(",");
        for (String s : types) {
            if (type.equals(s)) {
                return true;
            }

        }
        throw new Exception("文件类型不符合要求");
    }

    @Override
    public List<CenterNode> readCsv(String path) throws IOException {
        List<CenterNode> list = new ArrayList<CenterNode>();
        char delimiter = ',';
        CsvReader csvReader = new CsvReader(path, delimiter, Charset.forName("utf-8"));
        csvReader.setSkipEmptyRecords(true);
        csvReader.readHeaders();
        String s =csvReader.getHeader(1);
        String s1 = "具体地址";
        System.out.println("具体地址");
        System.out.println(s1);
        System.out.println(s);

        while(csvReader.readRecord())
        {
            CenterNode centerNode = new CenterNode();
            System.out.println("111111"+csvReader.get("中心编号"));
            System.out.println("2222222"+csvReader.get("地点名字"));
            centerNode.setcNum(csvReader.get("中心编号"));
            centerNode.setCName(csvReader.get("地点名字"));
            centerNode.setCAddress(csvReader.get("具体地址"));
            centerNode.setCType(Integer.valueOf(csvReader.get("类型")));
            centerNode.setCQuatity(Double.valueOf(csvReader.get("存储量（元）")));
            list.add(centerNode);
        }
        return list;


    }

    @Override
    public List<CenterNode> removeDuplication(List<CenterNode> list) throws Exception {
        int i=1;
        //删除不完整数据
        List<CenterNode> list1 = list.stream().filter((e)->e.getcNum()==null||"".equals(e.getcNum())).collect(Collectors.toList());
        List<CenterNode> list2 = list1.stream().filter((e)->e.getCName()==null||"".equals(e.getCName())).collect(Collectors.toList());
        List<CenterNode> list3 = list2.stream().filter((e)->e.getCAddress()==null||"".equals(e.getCAddress())).collect(Collectors.toList());
        List<CenterNode> list4 = list3.stream().filter((e)->e.getCType()==null||"".equals(e.getCType())).collect(Collectors.toList());
        List<CenterNode> list5 = list4.stream().filter((e)->e.getCQuatity()==null||"".equals(e.getCQuatity())).collect(Collectors.toList());
        //去除重复数据
        Iterator<CenterNode> iterator = list5.iterator();
        Set<String> set = new HashSet<String>();
        while (iterator.hasNext()) {
            String num = iterator.next().getcNum();
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
