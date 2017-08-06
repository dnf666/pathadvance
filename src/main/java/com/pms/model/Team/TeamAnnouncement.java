package com.pms.model.Team;

/**
 * Created by liudong on 2017/7/31.
 * 团队公告信息
 */
public class TeamAnnouncement {
    private String title;
    private String create_by;
    private String create_time;
    private String contex;
    private String team_name;
    private int del_flag;
    private String del_time;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getContex() {
        return contex;
    }

    public void setContex(String contex) {
        this.contex = contex;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }
}
