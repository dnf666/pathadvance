package com.pms.model.file;

/**
 * Created by Chenmeiling on 2017/8/26.
 */
public class FileImpl {
    private int id;
    private String fileName;
    private String url;
    private String fileClass;
    private int size;
    private String createBy;
    private String createTime;
    private String teamName;
    private boolean delFlag;
    private String delTime;
    private boolean isPrivater;

    public Integer getId(){ return id; }

    public void setId(int id){ this.id = id; }

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

    public String getTeamName(){ return teamName; }

    public void setTeamName(String teamName){ this.teamName = teamName; }

    public boolean getDelFlag() { return delFlag; }

    public void setDelFlag(boolean delFlag) { this.delFlag = delFlag; }

    public String getDelTime() { return delTime; }

    public void setDelTime(String delTime) { this.delTime = delTime; }

    public boolean getIsPrivater() { return isPrivater; }

    public void setIsPrivater(boolean privater) { isPrivater = privater; }
}
