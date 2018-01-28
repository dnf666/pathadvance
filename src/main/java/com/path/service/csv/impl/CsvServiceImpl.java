package com.path.service.csv.impl;

import com.path.service.csv.CsvService;

import java.io.File;
import java.util.List;

/**
 * @author demo
 */
public class CsvServiceImpl implements CsvService {

    @Override
    public boolean ensureType(File file) {
        String name = file.getName();
       int mine = name.lastIndexOf(".");
       String type = name.substring(mine+1);
       try{
           if(!type.equals("csv"))
           {
               throw new Exception("文件类型不符合要求");
           }
           return true;
       }catch (Exception e){
           return false;
       }
    }

    @Override
    public List readCsv(File file) {
        return null;
    }

    @Override
    public List removeDuplication(List list) {
        return null;
    }

    @Override
    public boolean storeDatabase(List list) {
        return false;
    }
}
