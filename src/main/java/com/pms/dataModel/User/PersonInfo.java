package com.pms.dataModel.User;

import com.pms.model.user.User;

/**
 * Created by rhan on 2017/7/22.
 * 这个类是用来给前端返回用户信息是用的一个类型
 * 例如当用户处于个人页面的时候，就会返回这些信息
 * 这些信息可以对外公开，而且不是敏感信息
 */
public class PersonInfo {
    private String userName;//用户名
    private String picture;//头像地址
    private String stuId;//学号
    private String peofession;//专业

    public PersonInfo(User user){
        this.userName = user.getUserName();
        this.picture = user.getPicture();
        this.stuId = user.getStuId();
        this.peofession = user.getPeofession();
    }

    public PersonInfo(){

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

    public String getPeofession() {
        return peofession;
    }

    public void setPeofession(String peofession) {
        this.peofession = peofession;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    @Override
    public String toString() {
        return userName+" "+picture+" "+stuId+" "+peofession;
    }
}
