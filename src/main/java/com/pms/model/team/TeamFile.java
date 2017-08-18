package com.pms.model.team;

/**
 * Created by liudong on 2017/8/13.
 */
public class TeamFile {
    private String fileName;
    private String url;//文件在 服务器上的地址
    private String fileClass;//文件分类
    private double fileSize;//文件大小
    private String createBy;
    private int delFlag;
    private String delTime;
    private int isPrivater;//是否私有

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileClass() {
        return fileClass;
    }

    public void setFileClass(String fileClass) {
        this.fileClass = fileClass;
    }

    public double getFileSize() {
        return fileSize;
    }

    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelTime() {
        return delTime;
    }

    public void setDelTime(String delTime) {
        this.delTime = delTime;
    }

    public int getIsPrivater() {
        return isPrivater;
    }

    public void setIsPrivater(int isPrivater) {
        this.isPrivater = isPrivater;
    }
}
