package com.pms.model.team;

/**
 * Created by liudong on 2017/8/19.
 */
public class TeamMasterHistory {
    private int id;
    private String teamName;
    private String userName;
    private String toRole;
    private String fromRole;
    private String modifyBy;//修正者
    private String ModifyAt;//修正时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToRole() {
        return toRole;
    }

    public void setToRole(String toRole) {
        this.toRole = toRole;
    }

    public String getFromRole() {
        return fromRole;
    }

    public void setFromRole(String fromRole) {
        this.fromRole = fromRole;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getModifyAt() {
        return ModifyAt;
    }

    public void setModifyAt(String modifyAt) {
        ModifyAt = modifyAt;
    }

}
