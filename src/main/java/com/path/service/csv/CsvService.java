package com.path.service.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @author demo
 */
public interface CsvService<T> {
    /**
     * 确认类型
     * @param file 待确认的文件类型
     * @return
     */
   boolean ensureType(File file,String path) throws Exception;

    List<T> readCsv(String path) throws FileNotFoundException;
    List<T> removeDuplication(T t);
       boolean storeDatabase(T t);

}
