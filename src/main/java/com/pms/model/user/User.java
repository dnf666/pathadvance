package com.pms.model.user;

import java.util.Date;

/**
 * Created by rhan on 2017/7/22.
 */
public class User {
    private String userName;
    private String password;
    private String picture;
    private String stuId;
    private String peofession;
    private Date createAt;
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

    public String getPeofession() {
        return peofession;
    }

    public void setPeofession(String peofession) {
        this.peofession = peofession;
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

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getDelRemarks() {
        return delRemarks;
    }

    public void setDelRemarks(String delRemarks) {
        this.delRemarks = delRemarks;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return userName+" "+password+" "+picture+" "+stuId+
                " " +peofession+" "+createAt+" "+delFlag+" "+delRemarks;
    }
}
