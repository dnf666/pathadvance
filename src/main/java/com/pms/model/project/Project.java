package com.pms.model.project;

/**
 * CreatedBy: liudong
 * On: 2017/9/11.
 * describle:
 */
public class Project {
    private int id;
    private String projectName;
    private String teamName;
    private String projectInfo;
    private String createBy;
    private String createAt;
    private int delFlag;

    private Project(Builder builder) {
        setId(builder.id);
        setProjectName(builder.projectName);
        setTeamName(builder.teamName);
        setProjectInfo(builder.projectInfo);
        setCreateBy(builder.createBy);
        setCreateAt(builder.createAt);
        delFlag = builder.delFlag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getProjectInfo() {
        return projectInfo;
    }

    public void setProjectInfo(String projectInfo) {
        this.projectInfo = projectInfo;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }



    public int getDelFlag() {
        return delFlag;
    }


    public static final class Builder {
        private int id;
        private String projectName;
        private String teamName;
        private String projectInfo;
        private String createBy;
        private String createAt;
        private int delFlag;

        public Builder() {
        }

        public Builder id(int val) {
            id = val;
            return this;
        }

        public Builder projectName(String val) {
            projectName = val;
            return this;
        }

        public Builder teamName(String val) {
            teamName = val;
            return this;
        }

        public Builder projectInfo(String val) {
            projectInfo = val;
            return this;
        }

        public Builder createBy(String val) {
            createBy = val;
            return this;
        }

        public Builder createAt(String val) {
            createAt = val;
            return this;
        }

        public Builder delFlag(int val) {
            delFlag = val;
            return this;
        }

        public Project build() {
            return new Project(this);
        }
    }
}
