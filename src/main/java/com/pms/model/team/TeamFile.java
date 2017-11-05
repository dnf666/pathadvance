package com.pms.model.team;

/**
 * Created by liudong on 2017/8/13.
 */
public class TeamFile {
    private  String teamName;
    private String fileName;
    private String url;//文件在 服务器上的地址
    private String fileClass;//文件分类
    private double fileSize;//文件大小
    private String createBy;
    private boolean delFlag;
    private String delTime;
    private int isPrivater;//是否私有
    public TeamFile(){};
    private TeamFile(Builder builder) {
        setTeamName(builder.teamName);
        setFileName(builder.fileName);
        setUrl(builder.url);
        setFileClass(builder.fileClass);
        setFileSize(builder.fileSize);
        setCreateBy(builder.createBy);
        setDelFlag(builder.delFlag);
        setDelTime(builder.delTime);
        setIsPrivater(builder.isPrivater);
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

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

    public boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(boolean delFlag) {
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


    public static final class Builder {
        private String teamName;
        private String fileName;
        private String url;
        private String fileClass;
        private double fileSize;
        private String createBy;
        private boolean delFlag;
        private String delTime;
        private int isPrivater;

        public Builder() {
        }

        public Builder teamName(String val) {
            teamName = val;
            return this;
        }

        public Builder fileName(String val) {
            fileName = val;
            return this;
        }

        public Builder url(String val) {
            url = val;
            return this;
        }

        public Builder fileClass(String val) {
            fileClass = val;
            return this;
        }

        public Builder fileSize(double val) {
            fileSize = val;
            return this;
        }

        public Builder createBy(String val) {
            createBy = val;
            return this;
        }

        public Builder delFlag(boolean val) {
            delFlag = val;
            return this;
        }

        public Builder delTime(String val) {
            delTime = val;
            return this;
        }

        public Builder isPrivater(int val) {
            isPrivater = val;
            return this;
        }

        public TeamFile build() {
            return new TeamFile(this);
        }
    }
}
