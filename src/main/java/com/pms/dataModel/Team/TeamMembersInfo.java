package com.pms.dataModel.Team;

/**
 * Created by liudong on 2017/7/31.
 * 团队成员信息
 */
public class TeamMembersInfo {
    private String team_name;
    private String user_name;
    private String team_role;
    private String join_time;
    private String join_by;

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
}
