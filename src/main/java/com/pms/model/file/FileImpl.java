package com.pms.model.file;

/**
 *
 * @author Chenmeiling
 * @date 2017/8/26
 */
public class FileImpl {
    private int fileId;
    private String fileName;
    private String url;//文件位置
    private String fileClass;//文件类型
    private int size;
    private String createBy;
    private String createTime;
    private int delFlag;
    private String delTime;
    private boolean isPrivater;

    public Integer getFileId(){ return fileId; }

    public void setFileId(int fileId){ this.fileId = fileId; }

    public String getFileName() { return fileName; }

    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    public String getFileClass() { return fileClass; }

    public void setFileClass(String fileClass) { this.fileClass = fileClass; }

    public int getSize() { return size; }

    public void setSize(int size) { this.size = size; }

    public String getCreateBy() { return createBy; }

    public void setCreateBy(String createBy) { this.createBy = createBy; }

    public String getCreateTime() { return createTime; }

    public void setCreateTime(String createTime) { this.createTime = createTime; }

    public int getDelFlag() { return delFlag; }

    public void setDelFlag(int delFlag) { this.delFlag = delFlag; }

    public String getDelTime() { return delTime; }

    public void setDelTime(String delTime) { this.delTime = delTime; }

    public boolean getIsPrivater() { return isPrivater; }

    public void setIsPrivater(boolean privater) { isPrivater = privater; }
}
