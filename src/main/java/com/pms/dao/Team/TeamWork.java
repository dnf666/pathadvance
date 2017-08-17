//package com.pms.dao.Team;
//
//import com.pms.dataModel.Team.*;
//
//import org.springframework.stereotype.Repository;
//
///**
// * Created by liudong on 2017/7/25.
// */
//@Repository
//public interface TeamWork {
//    //通过查询，得到相关的信息
//    public TeamMembersInfo SelectMembersByTeamName();//通过团队名称，得到相应成员的相关信息，完成
//    public TeamAnnouncementInfo SelectAnnouncementByName();//通过团队名称，得到相应的公告信息，完成
//    public TeamDataInfo SelectDataByClass();//通过分类（有一套特殊的命名规则），得到相应的团队资料,完成
//    public TeamProjectInfo SelectProjectsByTeamName();//通过团队名称得到相应的团队项目信息，完成
//    public TeamProjectMembers SelectProjectMembersByProjectName();//通过项目名称获取项目成员信息，完成
//    public Team SelectTeamInfoByTeamName();//通过团队名称得到相应的团队信息，完成
//
//    //关于团队成员
//    public boolean AddMembers();//添加新成员，完成
//    public boolean DelMembers();//删除新成员，完成
//    public boolean UpdateMemberPrivilege();//更改成员的权限，只有超级管理员能够操作,完成
//    public boolean DeliverPriviledge();//移交超级管理员的权限，只有超级管理员能够操作
//    //关于项目成员
//    public boolean AddProjectMembers();//添加项目新成员，完成
//    public boolean DelProjectMembers();//删除项目新成员，完成
//    //关于公告
//    public boolean AddAnnouncements();//新建公告,完成
//    public boolean ReEditAnnouncements();//更改公告，完成
//    public boolean DelAnnouncements();//删除公告，完成
//    //关于项目
//    public boolean AddProject();//新建项目
//    public boolean UpdateProject();//更改项目的一些信息
//    public boolean DelProject();//删除项目
//    //关于资料
//    public boolean AddData();//新建资料,完成
//    public boolean DelData();//删除资料，完成
//    //关于团队
//    public boolean AddTeam();//新建团队，完成
//    public boolean UpdateTeam();//更改团队的相关信息，完成
//    public boolean DelTeam();//注销团队，完成
//    }
