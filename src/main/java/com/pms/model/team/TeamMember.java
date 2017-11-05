package com.pms.model.team;

/**
 * Created by liudong on 2017/8/10.
 */
public class TeamMember {
    private String userName;
    private String teamName;
    private String teamRole;//团队角色
    private String joinTime;
    private String joinBy;//通过谁加入此团队的
    private boolean delFlag;
    private String delTime;
    private String delBy;
    private String delRemarks;
    private int teamPrivelige;//团队权限，0表示普通成员，1，表示管理员，2表示超级管理员即群主

    public TeamMember(){};
    private TeamMember(Builder builder) {
        setUserName(builder.userName);
        setTeamName(builder.teamName);
        setTeamRole(builder.teamRole);
        setJoinTime(builder.joinTime);
        setJoinBy(builder.joinBy);
        setDelFlag(builder.delFlag);
        setDelTime(builder.delTime);
        setDelBy(builder.delBy);
        setDelRemarks(builder.delRemarks);
        setTeamPrivelige(builder.teamPrivelige);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
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

    public boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(boolean delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelTime() {
        return delTime;
    }

    public void setDelTime(String delTime) {
        this.delTime = delTime;
    }

    public String getDelBy() {
        return delBy;
    }

    public void setDelBy(String delBy) {
        this.delBy = delBy;
    }

    public String getDelRemarks() {
        return delRemarks;
    }

    public void setDelRemarks(String delRemarks) {
        this.delRemarks = delRemarks;
    }

    public int getTeamPrivelige() {
        return teamPrivelige;
    }

    public void setTeamPrivelige(int teamPrivelige) {
        this.teamPrivelige = teamPrivelige;
    }


    public static final class Builder {
        private String userName;
        private String teamName;
        private String teamRole;
        private String joinTime;
        private String joinBy;
        private boolean delFlag;
        private String delTime;
        private String delBy;
        private String delRemarks;
        private int teamPrivelige;

        public Builder() {
        }

        public Builder userName(String val) {
            userName = val;
            return this;
        }

        public Builder teamName(String val) {
            teamName = val;
            return this;
        }

        public Builder teamRole(String val) {
            teamRole = val;
            return this;
        }

        public Builder joinTime(String val) {
            joinTime = val;
            return this;
        }

        public Builder joinBy(String val) {
            joinBy = val;
            return this;
        }

        public Builder delFlag(boolean val) {
            delFlag = val;
            return this;
        }

        public Builder delTime(String val) {
            delTime = val;
            return this;
        }

        public Builder delBy(String val) {
            delBy = val;
            return this;
        }

        public Builder delRemarks(String val) {
            delRemarks = val;
            return this;
        }

        public Builder teamPrivelige(int val) {
            teamPrivelige = val;
            return this;
        }

        public TeamMember build() {
            return new TeamMember(this);
        }
    }
}
