package com.pms.service.FileReference.Impl;

import com.pms.dao.fileReference.FileReferenceMapper;
import com.pms.model.file.FileImpl;
import com.pms.service.FileReference.FileReferenceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FileReferenceServiceImpl implements FileReferenceService {

    @Resource
    private FileReferenceMapper fileReferenceMapper;

    public List<FileImpl> getFilesByProjectId(int projectId) {

        return fileReferenceMapper.getFilesByProjectId(projectId);

    }
}
