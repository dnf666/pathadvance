package com.pms.model.team;

/**
 * Created by liudong on 2017/8/13.
 */
public class TeamNotice {
    private int id;
    private String title;
    private String createBy;
    private String createTime;
    private boolean delFlag;
    private String delTime;
    private String context;
    private String teamName;

    public TeamNotice(){};
    private TeamNotice(Builder builder) {
        setId(builder.id);
        setTitle(builder.title);
        setCreateBy(builder.createBy);
        setCreateTime(builder.createTime);
        setDelFlag(builder.delFlag);
        setDelTime(builder.delTime);
        setContext(builder.context);
        setTeamName(builder.teamName);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public static final class Builder {
        private int id;
        private String title;
        private String createBy;
        private String createTime;
        private boolean delFlag;
        private String delTime;
        private String context;
        private String teamName;

        public Builder() {
        }

        public Builder id(int val) {
            id = val;
            return this;
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder createBy(String val) {
            createBy = val;
            return this;
        }

        public Builder createTime(String val) {
            createTime = val;
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

        public Builder context(String val) {
            context = val;
            return this;
        }

        public Builder teamName(String val) {
            teamName = val;
            return this;
        }

        public TeamNotice build() {
            return new TeamNotice(this);
        }
    }
}
