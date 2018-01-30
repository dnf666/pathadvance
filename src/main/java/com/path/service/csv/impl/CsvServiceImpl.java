package com.path.service.csv.impl;

import com.csvreader.CsvReader;
import com.path.model.CenterNode;
import com.path.model.ServiceNode;
import com.path.model.Vahicle;
import com.path.service.csv.CsvService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author demo
 */
@Service
public class CsvServiceImpl<T> implements CsvService<T> {
    private static CenterNode centerNode = new CenterNode();

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
    public List<T> readCsv(String path) throws FileNotFoundException {
        List<T> list = new ArrayList<T>();
        char delimiter = ',';
        CsvReader csvReader =null;
        try {
            csvReader = new CsvReader(path, delimiter, Charset.forName("GBK"));
            csvReader.setSkipEmptyRecords(true);
            csvReader.getHeaders();
            while(csvReader.readRecord())
            {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<T> removeDuplication(T t) {
        return null;
    }


    @Override
    public boolean storeDatabase(T t) {
        return false;
    }


}
