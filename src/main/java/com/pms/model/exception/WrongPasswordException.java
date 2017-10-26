package com.pms.model.exception;

public class WrongPasswordException extends RuntimeException {

    private String name;

    public WrongPasswordException(){
        super("密码错误");
    }

    public WrongPasswordException(String name){
        super("用户 " + name + " 不存在或者密码错误");
        this.name = name;
    }

}
