package com.pms.dataModel.User;

import com.pms.model.user.User;

/**
 * Created by rhan on 2017/7/22.
 */
public class PersonInfo {
    private String userName;
    private String picture;
    private int stuId;
    private String peofession;

    public PersonInfo(User user){
        this.userName = user.getUserName();
        this.picture = user.getPicture();
        this.stuId = user.getStuId();
        this.peofession = user.getPeofession();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
}
