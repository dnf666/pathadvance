package com.pms.model.filereference;

/**
 * @author MEI
 */
public class FileReference {
    private int fileId;
    private int teamName;
    private int projectId;
    private int userName;

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public int getTeamName() {
        return teamName;
    }

    public void setTeamName(int teamName) {
        this.teamName = teamName;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getUserName() {
        return userName;
    }

    public void setUserName(int userName) {
        this.userName = userName;
    }
}
