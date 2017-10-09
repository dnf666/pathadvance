package com.pms.model.project;

/**
 * CreatedBy: liudong
 * On: 2017/9/10.
 * describle:
 */
// 项目归属
public class ProjectReference {
    private int projectId;
    private int teamId;

    //删除标志
    private int delFlag;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }
}
