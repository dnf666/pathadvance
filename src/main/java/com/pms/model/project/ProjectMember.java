package com.pms.model.project;

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
    private String teamName;

    private ProjectMember(){}

    private ProjectMember(Builder builder) {
        setProjectName(builder.projectName);
        setUserName(builder.userName);
        setTeamRole(builder.teamRole);
        setJoinTime(builder.joinTime);
        setJoinBy(builder.joinBy);
        setDelFlag(builder.delFlag);
        setDelTime(builder.delTime);
        setDelRemarks(builder.delRemarks);
        setDelBy(builder.delBy);
        setTeamName(builder.teamName);
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

    public String getDelBy() {
        return delBy;
    }

    public void setDelBy(String delBy) {
        this.delBy = delBy;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }


    public static final class Builder {
        private String projectName;
        private String userName;
        private String teamRole;
        private String joinTime;
        private String joinBy;
        private int delFlag;
        private String delTime;
        private String delRemarks;
        private String delBy;
        private String teamName;

        public Builder() {
        }

        public Builder projectName(String val) {
            projectName = val;
            return this;
        }

        public Builder userName(String val) {
            userName = val;
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

        public Builder delFlag(int val) {
            delFlag = val;
            return this;
        }

        public Builder delTime(String val) {
            delTime = val;
            return this;
        }

        public Builder delRemarks(String val) {
            delRemarks = val;
            return this;
        }

        public Builder delBy(String val) {
            delBy = val;
            return this;
        }

        public Builder teamName(String val) {
            teamName = val;
            return this;
        }

        public ProjectMember build() {
            return new ProjectMember(this);
        }
    }
}
