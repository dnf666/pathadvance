package com.pms.dataModel.Team;

/**
 * Created by liudong on 2017/7/31.
 * 团队资料
 */
public class TeamDataInfo {
    private String file_name;//文件名称
    private String url;//存储在服务器上面的地址
    private String size;//文件大小
    private String create_by;
    private String create_time;
    private int del_flag;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(int del_flag) {
        this.del_flag = del_flag;
    }

    public int getIs_privater() {
        return is_privater;
    }

    public void setIs_privater(int is_privater) {
        this.is_privater = is_privater;
    }
}
