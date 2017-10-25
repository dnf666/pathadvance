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
<<<<<<< HEAD
        return fileMapper.insertFileInfo(fileImpl);
    }

    @Override
    public boolean selectByFileId(FileImpl fileImpl, int fileId){
        if (fileId == (fileImpl.getFileId())) {
            if (fileMapper.selectByFileId(fileId)) {
                return true;
            }
        }
        return false;
=======
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
    public boolean selectByFileId(int fileId){
        try {
            fileMapper.selectByFileId(fileId);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("查找失败");
            return false;
        }
        return true;
>>>>>>> master
    }


    @Override
<<<<<<< HEAD
    public boolean updateFileInfo(FileImpl fileImpl, String fileName) {
        if (fileName.equals(fileImpl.getFileName())) {
            if (fileMapper.updateFileInfo(fileImpl)) {
                return true;
            }
        }
            return false;
    }

    @Override
    public boolean deleteByDelFlag(FileImpl fileImpl, int fileId){
        if (fileId == (fileImpl.getFileId())) {
            if (fileMapper.deleteByDelFlag(fileImpl)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean recoverFile(FileImpl fileImpl, int fileId) {
        if (fileId == (fileImpl.getFileId())) {
            if (fileMapper.recoverFile(fileImpl)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteFile(FileImpl fileImpl, int fileId) {
        if (fileId == (fileImpl.getFileId())) {
            if (fileMapper.deleteFile(fileImpl, fileId)) {
                return true;
            }
        }
        return false;
=======
    public boolean updateFileInfo(String fileName) {
        FileImpl fileImpl = new FileImpl();
        if (fileName != null) {
            fileMapper.updateFileInfo(fileImpl);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByDelFlag(int fileId){
        FileImpl fileImpl = new FileImpl();
        try {
            fileMapper.deleteByDelFlag(fileImpl);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("删除失败");
            return false;
        }
        return true;
    }

    @Override
    public boolean recoverFile(int fileId) {
        FileImpl fileImpl = new FileImpl();
        try {
            fileMapper.recoverFile(fileImpl);
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
>>>>>>> master
    }

    @Override
    public boolean downloadFile(String fileName){
        return true;
    }

}
