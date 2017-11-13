package com.pms.service.file.Impl;

import com.pms.dao.file.FileMapper;
import com.pms.model.file.FileImpl;
import com.pms.service.file.FileService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 *
 * @author Chenmeiling
 * @date 2017/8/18
 */
@Service
public class FileServiceImpl implements FileService {

    @Resource
    private FileMapper fileMapper;

    @Override
    public boolean insertFileInfo(FileImpl fileImpl) {
        try {
            fileMapper.insertFileInfo(fileImpl);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("添加失败");
            return false;
        }
        return true;
    }

    @Override
    public FileImpl selectByFileId(int fileId){
        return fileMapper.selectByFileId(fileId);
    }

    @Override
    public boolean updateFileInfo(String fileName,int fileId) {
        try {
            fileMapper.updateFileInfo(fileName,fileId);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("修改失败");
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteByDelFlag(int fileId){
        try {
            fileMapper.deleteByDelFlag(fileId);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("删除失败");
            return false;
        }
        return true;
    }

    @Override
    public boolean recoverFile(int fileId) {
        try {
            fileMapper.recoverFile(fileId);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("恢复失败");
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteFile(int fileId) {
        try {
            fileMapper.deleteFile(fileId);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("删除失败");
            return false;
        }
        return true;
    }

    @Override
    public boolean downloadFile(int fileId){
        return true;
    }

}
