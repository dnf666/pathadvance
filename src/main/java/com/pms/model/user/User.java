package com.pms.model.user;

import java.util.Date;

/**
 * Created by rhan on 2017/7/22.
 * 用户的映射类
 */
public class User {
    private String userName;//用户名
    private String password;//密码
    private String picture;//头像地址
    private String stuId;//学号
    private String peofession;//专业
    private Date createAt;//创建的时间
    private boolean delFlag;//用户是否被停用的标记
    private String delRemarks;//停用的备注，例如恶意灌水
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
