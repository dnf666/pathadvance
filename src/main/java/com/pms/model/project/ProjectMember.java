package com.pms.model.project;

/**
 *
 * @author liudong
 * @date 2017/8/11
 */


public class ProjectMember {
    private int projectId;
    private String userName;
    private String projectRole;
    private String joinTime;
    private String joinBy;
    private boolean delFlag;
    private String delTime;
    private String delRemarks;
    private String delBy;
    private String teamName;

    public ProjectMember(){}

    private ProjectMember(Builder builder) {
        setProjectId(builder.projectId);
        setUserName(builder.userName);
        setProjectRole(builder.projectRole);
        setJoinTime(builder.joinTime);
        setJoinBy(builder.joinBy);
        setDelFlag(builder.delFlag);
        setDelTime(builder.delTime);
        setDelRemarks(builder.delRemarks);
        setDelBy(builder.delBy);
        setTeamName(builder.teamName);
    }

    @Override
    public String toString() {
        return "ProjectMember{" +
                "projectId=" + projectId +
                ", userName='" + userName + '\'' +
                ", projectRole='" + projectRole + '\'' +
                ", joinTime='" + joinTime + '\'' +
                ", joinBy='" + joinBy + '\'' +
                ", delFlag=" + delFlag +
                ", delTime='" + delTime + '\'' +
                ", delRemarks='" + delRemarks + '\'' +
                ", delBy='" + delBy + '\'' +
                ", teamId='" + teamName + '\'' +
                '}';
    }
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProjectRole() {
        return projectRole;
    }

    public void setProjectRole(String projectRole) {
        this.projectRole = projectRole;
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

    public boolean isDelFlag() {
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
        private int projectId;
        private String userName;
        private String projectRole;
        private String joinTime;
        private String joinBy;
        private boolean delFlag;
        private String delTime;
        private String delRemarks;
        private String delBy;
        private String teamName;

        public Builder() {
        }

        public Builder projectId(int val) {
            projectId = val;
            return this;
        }

        public Builder userName(String val) {
            userName = val;
            return this;
        }

        public Builder projectRole(String val) {
            projectRole = val;
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
