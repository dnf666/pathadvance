package com.path.service.csv;

import com.path.model.CenterNode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @author demo
 */
public interface CenterCsvService {
    /**
     * 确认类型
     * @param file 待确认的文件类型
     * @return
     */
   boolean ensureType(File file,String path) throws Exception;

    List<CenterNode> readCsv(String path) throws IOException;
    List<CenterNode> removeDuplication(List<CenterNode> list) throws Exception;
       boolean storeDatabase(List<CenterNode> list);

}
