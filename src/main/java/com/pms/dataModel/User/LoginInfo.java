package com.pms.dataModel.User;

/**
 * Created by rhan on 2017/7/22.
 * 这个类是用来SpringMVC映射登陆消息的
 * 关于delFlag，如果delFlag是true的话，那么这个用户
 * 就处于停用的状态，如果不是true那么就正常使用
 */
public class LoginInfo {
    private String userName;//用户名
    private String password;//密码
    private Boolean delFlag;//是否被删除
    private String verificationCode;//验证码

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

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }
}
