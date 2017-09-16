package com.pms.service.file.Impl;

import com.pms.dao.file.FileMapper;
import com.pms.model.file.FileImpl;
import com.pms.service.file.FileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

//import com.pms.model.file.File;

/**
 * Created by Chenmeiling on 2017/8/18.
 */
@Service
public class FileServiceImpl implements FileService {

    @Resource
    private FileMapper fileMapper;

    public boolean insertFileInfo(FileImpl fileImpl) {
        if (fileMapper.insertFileInfo(fileImpl))
            return true;
        return false;
    }

    public List<FileImpl> selectByFileName(String fileName){
        return fileMapper.selectByFileName(fileName);
    }


    public boolean updateFileInfo(FileImpl fileImpl) {
        if (fileMapper.updateFileInfo(fileImpl)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteByDelFlag(FileImpl fileImpl,String fileName,String teamName){
        if (fileName.equals(fileImpl.getFileName()) && teamName.equals(fileImpl.getTeamName()))
            if (fileMapper.deleteByDelFlag(fileImpl))
                return true;
            return false;
    }

    public boolean recoverFile(FileImpl fileImpl,String fileName,String teamName) {
        if (fileName.equals(fileImpl.getFileName()) && teamName.equals(fileImpl.getTeamName()))
            if (fileMapper.recoverFile(fileImpl))
                return true;
        return false;
    }

    public boolean deleteFile(FileImpl fileImpl,String fileName,String teamName) {
        if (fileName.equals(fileImpl.getFileName()) && teamName.equals(fileImpl.getTeamName()))
            if (fileMapper.deleteFile(fileImpl,fileName,teamName))
                return true;
        return false;
    }

    public boolean downloadFile(String fileName){
        return true;
    }
}
