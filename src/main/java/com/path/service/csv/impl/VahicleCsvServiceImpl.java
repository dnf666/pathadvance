package com.path.service.csv.impl;

import com.csvreader.CsvReader;
import com.path.dao.VahicleMapper;
import com.path.model.CenterNode;
import com.path.model.Path;
import com.path.model.Vahicle;
import com.path.service.csv.CsvService;
import com.path.util.DateUtil;
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
public class VahicleCsvServiceImpl implements CsvService<Vahicle>{
    @Resource
    private VahicleMapper VahicleMapper;


    @Override
    public List<Vahicle> readCsv(Path path) throws IOException {
        List<Vahicle> list = new ArrayList<Vahicle>();
        char delimiter = ',';
        CsvReader csvReader = new CsvReader(path.getPath(), delimiter, Charset.forName("gbk"));
        csvReader.setSkipEmptyRecords(true);
        csvReader.readHeaders();

        while(csvReader.readRecord())
        {
            Vahicle Vahicle = new Vahicle();
            Vahicle.setVNum(Integer.valueOf(csvReader.get("汽车编号")));
            Vahicle.setVType(csvReader.get("汽车类型"));
            Vahicle.setVCapacity(Float.valueOf(csvReader.get("运钞量（元）")));
            Vahicle.setVOil(Float.valueOf(csvReader.get("耗油量（升/百公里每小时）")));
            Vahicle.setVPrice(Float.valueOf(csvReader.get("运费（元/每公里）")));
            Vahicle.setVDate(DateUtil.stringToDate(csvReader.get("出厂日期")));
            list.add(Vahicle);
        }
        return list;
    }

    @Override
    public List<Vahicle> removeDuplication(List<Vahicle> list) throws Exception {
        int i=1;
        //删除不完整数据
        List<Vahicle> list1 = list.stream().filter((e)->e.getVNum()==null||"".equals(e.getVNum())).collect(Collectors.toList());
        List<Vahicle> list2 = list1.stream().filter((e)->e.getVType()==null||"".equals(e.getVType())).collect(Collectors.toList());
        List<Vahicle> list3 = list2.stream().filter((e)->e.getVCapacity()==null||"".equals(e.getVCapacity())).collect(Collectors.toList());
        List<Vahicle> list4 = list3.stream().filter((e)->e.getVDate()==null||"".equals(e.getVDate())).collect(Collectors.toList());
        List<Vahicle> list5 = list4.stream().filter((e)->e.getVPrice()==null||"".equals(e.getVPrice())).collect(Collectors.toList());
        List<Vahicle> list6 = list5.stream().filter((e)->e.getVOil()==null||"".equals(e.getVOil())).collect(Collectors.toList());
        //去除重复数据
        Iterator<Vahicle> iterator = list6.iterator();
        Set<Integer> set = new HashSet<Integer>();
        while (iterator.hasNext()) {
            Integer num = iterator.next().getVNum();
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
    public boolean storeDatabase(List<Vahicle> list) {
        boolean result = VahicleMapper.insertAdvance(list);
        return result;
    }
}
