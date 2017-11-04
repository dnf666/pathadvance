package com.pms.model.team;

/**
 * CreatedBy: liudong
 * On: 2017/9/10.
 * describle:
 */
public class FileReference {
    private int fileId;
    private int teamId;
    private int projectId;
    private int userId;
    public FileReference(){};
    private FileReference(Builder builder) {
        setFileId(builder.fileId);
        setTeamId(builder.teamId);
        setProjectId(builder.projectId);
        setUserId(builder.userId);
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public static final class Builder {
        private int fileId;
        private int teamId;
        private int projectId;
        private int userId;

        public Builder() {
        }

        public Builder fileId(int val) {
            fileId = val;
            return this;
        }

        public Builder teamId(int val) {
            teamId = val;
            return this;
        }

        public Builder projectId(int val) {
            projectId = val;
            return this;
        }

        public Builder userId(int val) {
            userId = val;
            return this;
        }

        public FileReference build() {
            return new FileReference(this);
        }
    }
}
