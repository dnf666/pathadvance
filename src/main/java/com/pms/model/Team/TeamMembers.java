package com.pms.model.Team;

/**
 * Created by liudong on 2017/7/26.
 */
public class TeamMembers {
    private String user_name;
    private String team_name;
    private String team_role;
    private String join_time;
    private String join_by;
    private int del_flag;
    private String del_time;
    private String del_by;
    private String del_remarks;
    private int team_privelige;

    public int getTeam_privelige() {
        return team_privelige;
    }

    public void setTeam_privelige(int team_privelige) {
        this.team_privelige = team_privelige;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getTeam_role() {
        return team_role;
    }

    public void setTeam_role(String team_role) {
        this.team_role = team_role;
    }

    public String getJoin_time() {
        return join_time;
    }

    public void setJoin_time(String join_time) {
        this.join_time = join_time;
    }

    public String getJoin_by() {
        return join_by;
    }

    public void setJoin_by(String join_by) {
        this.join_by = join_by;
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

    public String getDel_by() {
        return del_by;
    }

    public void setDel_by(String del_by) {
        this.del_by = del_by;
    }

    public String getDel_remarks() {
        return del_remarks;
    }

    public void setDel_remarks(String del_remarks) {
        this.del_remarks = del_remarks;
    }

}
