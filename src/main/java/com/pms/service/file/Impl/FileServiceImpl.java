package com.pms.service.file.Impl;

import com.pms.dao.file.FileMapper;
import com.pms.model.file.File;
import com.pms.service.file.FileService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Chenmeiling on 2017/8/18.
 */
public class FileServiceImpl implements FileService {

    @Resource
    private FileMapper fileMapper;

    public boolean insertFileInfo(File file) {
        if (fileMapper.insertFileInfo(file)){
            return true;
        }else {
            return false;
        }
    }

    public File selectByFileName(List<File> list, String fileName) {
        if (list.size() == 0) {
            return null;
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (fileName.equals(list.get(i).getFileName()))
                    return list.get(i);
            }
        }
        return null;
    }


    public boolean updateFileInfo(File file) {
        if (fileMapper.updateFileInfo(file)){
            return true;
        }else {
            return false;
        }
    }

    public boolean deleteByDelFlag(File file) {
        if (fileMapper.deleteByDelFlag(file)){
            return true;
        }else {
            return false;
        }
    }

    public boolean recoverByDelFlag(File file) {
        if (fileMapper.recoverByDelFlag(file)){
            return true;
        }else {
            return false;
        }
    }

    public boolean deleteByFileName(String fileName) {
        if (fileMapper.deleteByFileName(fileName)){
            return true;
        }else {
            return false;
        }
    }
}
