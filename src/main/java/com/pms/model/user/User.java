package com.pms.model.user;

/**
 * Created by rhan on 2017/7/22.
 */
public class User {
    private String userName;
    private String password;
    private String picture;
    private int stuId;
    private String peofession;
    private long createAt;
    private boolean delFlag;
    private String delRemarks;
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public String getPeofession() {
        return peofession;
    }

    public void setPeofession(String peofession) {
        this.peofession = peofession;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    public boolean isDelFlag() {
        return delFlag;
    }

    public void setDelFlag(boolean delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelRemark() {
        return delRemarks;
    }

    public void setDelRemark(String delRemark) {
        this.delRemarks = delRemark;
    }
}
