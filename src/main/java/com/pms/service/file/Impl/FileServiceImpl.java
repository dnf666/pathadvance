package com.pms.service.file.Impl;

import com.pms.dao.file.FileMapper;
import com.pms.model.file.FileImpl;
import com.pms.service.file.FileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

<<<<<<< HEAD
    public FileImpl selectByFileId(int fileId){
        return fileMapper.selectByFileId(fileId);
=======
    public boolean selectByFileId(FileImpl fileImpl, int fileId){
        if (fileId == (fileImpl.getFileId()))
            if (fileMapper.selectByFileId(fileId))
                return true;
        return false;
>>>>>>> fc958eaab499b1ccdc9dfaa67c75f8d681ed86a0
    }


    public boolean updateFileInfo(FileImpl fileImpl,String fileName) {
        if (fileName.equals(fileImpl.getFileName()))
            if (fileMapper.updateFileInfo(fileImpl))
                return true;
            return false;
    }

    public boolean deleteByDelFlag(FileImpl fileImpl,int fileId){
        if (fileId == (fileImpl.getFileId()))
            if (fileMapper.deleteByDelFlag(fileImpl))
                return true;
            return false;
    }

    public boolean recoverFile(FileImpl fileImpl,int fileId) {
        if (fileId == (fileImpl.getFileId()))
            if (fileMapper.recoverFile(fileImpl))
                return true;
        return false;
    }

    public boolean deleteFile(FileImpl fileImpl,int fileId) {
        if (fileId == (fileImpl.getFileId()))
            if (fileMapper.deleteFile(fileImpl,fileId))
                return true;
        return false;
    }

    public boolean downloadFile(String fileName){
        return true;
    }
}
