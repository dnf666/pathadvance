package com.pms.model.team;

/**
 * Created by liudong on 2017/8/10.
 * 记录团队信息
 */
public class Team {
    private String teamName;//团队名称
    private String createBy;//团队创建者
    private String createTime;//团队创建时间
    private int delFlag;//删除标志
    private String delTime;//删除时间
    private String delRemarks;//删除的备注

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
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
}
