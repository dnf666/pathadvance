package com.pms.model.file;

/**
 * Created by Administrator on 2017/8/1.
 */
public class File {
    private String fileName;
    private String url;
    private String fileClass;
    private int size;
    private String createBy;
    private String createTime;
    private boolean delFlag;
    private String delTime;
    private boolean isPrivater;

    public String getFileName() { return fileName; }

    public void setFileName() { this.fileName = fileName; }

    public String getUrl() { return url; }

    public void setUrl() { this.url = url; }

    public String getFileClass() { return fileClass; }

    public void setFileClass(String fileClass) { this.fileClass = fileClass; }

    public int getSize() { return size; }

    public void setSize(int size) { this.size = size; }

    public String getCreateBy() { return createBy; }

    public void setCreateBy(String createBy) { this.createBy = createBy; }

    public String getCreateTime() { return createTime; }

    public void setCreateTime(String createTime) { this.createTime = createTime; }

    public boolean isDelFlag() { return delFlag; }

    public void setDelFlag(boolean delFlag) { this.delFlag = delFlag; }

    public String getDelTime() { return delTime; }

    public void setDelTime(String delTime) { this.delTime = delTime; }

    public boolean isPrivater() { return isPrivater; }

    public void setPrivater(boolean privater) { isPrivater = privater; }
}
