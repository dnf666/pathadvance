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

    public TeamMasterHistory(){};
    private TeamMasterHistory(Builder builder) {
        setId(builder.id);
        setTeamName(builder.teamName);
        setUserName(builder.userName);
        setToRole(builder.toRole);
        setFromRole(builder.fromRole);
        setModifyBy(builder.modifyBy);
        setModifyAt(builder.ModifyAt);
    }

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


    public static final class Builder {
        private int id;
        private String teamName;
        private String userName;
        private String toRole;
        private String fromRole;
        private String modifyBy;
        private String ModifyAt;

        public Builder() {
        }

        public Builder id(int val) {
            id = val;
            return this;
        }

        public Builder teamName(String val) {
            teamName = val;
            return this;
        }

        public Builder userName(String val) {
            userName = val;
            return this;
        }

        public Builder toRole(String val) {
            toRole = val;
            return this;
        }

        public Builder fromRole(String val) {
            fromRole = val;
            return this;
        }

        public Builder modifyBy(String val) {
            modifyBy = val;
            return this;
        }

        public Builder ModifyAt(String val) {
            ModifyAt = val;
            return this;
        }

        public TeamMasterHistory build() {
            return new TeamMasterHistory(this);
        }
    }
}
