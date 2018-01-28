package com.path.service.csv;

import java.io.File;
import java.util.List;

/**
 * @author demo
 */
public interface CsvService {
    /**
     * 确认类型
     * @param file 待确认的文件类型
     * @return
     */
   boolean ensureType(File file);

   List readCsv(File file);
   List removeDuplication(List list);
       boolean storeDatabase(List list);

}
