package com.pms.model.team;

/**
 * Created by liudong on 2017/8/11.
 */
public class ProjectMember {
   private String projectName;
   private String userName;
   private String teamRole;
   private String joinTime;
   private String joinBy;
    private int delFlag;
   private String delTime;
   private String delRemarks;
   private String delBy;

    public String getDelBy() {
        return delBy;
    }

    public void setDeLBy(String deLBy) {
        this.delBy = delBy;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTeamRole() {
        return teamRole;
    }

    public void setTeamRole(String teamRole) {
        this.teamRole = teamRole;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }

    public String getJoinBy() {
        return joinBy;
    }

    public void setJoinBy(String joinBy) {
        this.joinBy = joinBy;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelTime() {
        return delTime;
    }

    public void setDelTime(String delTime) {
        this.delTime = delTime;
    }

    public String getDelRemarks() {
        return delRemarks;
    }

    public void setDelRemarks(String delRemarks) {
        this.delRemarks = delRemarks;
    }
}
