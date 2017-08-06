package com.pms.model.Team;

/**
 * Created by liudong on 2017/8/1.
 */
public class TeamProject {
    private String project_name;
    private String team_name;
    private String project_info;//项目的简单介绍
    private String create_by;
    private String create_time;
    private int del_flag;

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getProject_info() {
        return project_info;
    }

    public void setProject_info(String project_info) {
        this.project_info = project_info;
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
}
