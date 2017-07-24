package com.pms.dataModel.User;

/**
 * Created by rhan on 2017/7/22.
 */
public class LoginInfo {
    private String userName;
    private String password;
    private String cerificationCode;

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

    public String getCerificationCode() {
        return cerificationCode;
    }

    public void setCerificationCode(String cerificationCode) {
        this.cerificationCode = cerificationCode;
    }
}
