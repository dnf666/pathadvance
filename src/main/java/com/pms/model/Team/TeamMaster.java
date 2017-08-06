package com.pms.model.Team;

/**
 * Created by liudong on 2017/7/26.
 */
public class TeamMaster {
    //因为id是自增长的所以没有写
    private String team_name;
    private String user_name;
    private String to_role;//修改前的职位
    private String from_role;//修改后的职位
    private String modify_by;
    private String modify_at;

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getTo_role() {
        return to_role;
    }

    public void setTo_role(String to_role) {
        this.to_role = to_role;
    }

    public String getFrom_role() {
        return from_role;
    }

    public void setFrom_role(String from_role) {
        this.from_role = from_role;
    }

    public String getModify_by() {
        return modify_by;
    }

    public void setModify_by(String modify_by) {
        this.modify_by = modify_by;
    }

    public String getModify_at() {
        return modify_at;
    }

    public void setModify_at(String modify_at) {
        this.modify_at = modify_at;
    }


}
