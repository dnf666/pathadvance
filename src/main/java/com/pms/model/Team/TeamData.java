package com.pms.model.Team;

/**
 * Created by liudong on 2017/8/1.
 */
public class TeamData {
    private String file_name;
    private String url;
    private String file_class;
    private double size;
    private String create_by;
    private int del_flag;
    private String del_time;
    private int is_privater;

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFile_class() {
        return file_class;
    }

    public void setFile_class(String file_class) {
        this.file_class = file_class;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public int getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(int del_flag) {
        this.del_flag = del_flag;
    }

    public String getDel_time() {
        return del_time;
    }

    public void setDel_time(String del_time) {
        this.del_time = del_time;
    }

    public int getIs_privater() {
        return is_privater;
    }

    public void setIs_privater(int is_privater) {
        this.is_privater = is_privater;
    }
}
