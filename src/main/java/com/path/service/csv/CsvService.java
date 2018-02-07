package com.path.service.csv;

import com.path.model.CenterNode;
import com.path.model.Path;
import com.path.util.PropertiesUtil;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.Properties;

/**
 * @author demo
 */

public interface CsvService<T> {
    /**
     * 确认类型
     * @param file 待确认的文件类型
     * @return
     */
  default boolean ensureType(File file,String path) throws Exception{
      final String fileName = "type.properties";
       {
           String name = file.getName();
           int mine = name.lastIndexOf(".");
           String type = name.substring(mine + 1);
          Properties properties = PropertiesUtil.propertitesUtil(path,fileName);
           String type1 = properties.getProperty("type");
           String types[] = type1.split(",");
           for (String s : types) {
               if (type.equals(s)) {
                   return true;
               }

           }
           throw new Exception("文件类型不符合要求");
       }
   }

    /**
     * 读取csv文件
     * @param path 文件路径
     * @return
     * @throws IOException
     */
    List<T> readCsv(Path path) throws IOException;

    /**
     *
     * @param list 原始数据
     * @return
     * @throws Exception
     */
    List<T> checkFile(List<T> list) throws Exception;

    /**
     * 存入数据库
     * @param list 最终数据
     * @return
     */
       boolean storeDatabase(List<T> list);

}
