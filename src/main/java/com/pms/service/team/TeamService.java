package com.pms.service.team;

import com.pms.model.file.File;
import com.pms.model.team.*;

import java.util.List;

/**
 * Created by liudong on 2017/8/15.
 *
 * 团队的service，包含了团队成员，团队项目，团队公告，团队项目成员以及团队文件
 *
 */
public interface TeamService {
    /**
     * 判断当前成员是否是当前的项目成员
     * @param list
     * @param userName
     * @return boolean
     */
    public boolean isProMember(List<ProjectMember> list, String userName);
    /**
     * 返回 public List<TeamMember> getTeamMembersByTeamName(String teamName),方法中需要的某一个特定成员
     *
     * @param  list
     * @param userName
     * @return TeamMember
     */
    public TeamMember getMember(List<TeamMember> list,String userName);
    /**
     * 得到用户在团队当中的权限。如果当前用户没有在当前的团队中返回-1
     * @param userName
     * @param teamMember
     */
    public int getTeamPrivilege(TeamMember teamMember,String userName);
    /**
     * @describle 创建一个新的团队， 默认创建团队者为最高权限者。
     * @param team :团队对象
     *  @return boolean
     */
    public boolean createTeam(Team team);
    /**
     * @describle 团队注销，只能够由团队负责人注销
     * @param team
     * @param delBy
     * @return boolean
     */
    public boolean delTeam(Team team ,String delBy);

    /**
     * @describle 添加团队成员，
     * 1.有一定的权限才能够添加
     * 2.如果被添加的成员已经在团队中就不能够再添加
     *
     * @param teamMember:邀请的团队成员对象
     * @retuen boolean
     */
    public boolean inviteMember(TeamMember teamMember);

    /**
     *@describle 移除团队成员
     * 1.有一定的权限才能够移除
     * 2.自己不能够移除自己，就是自己不能够主动推出当前团队
     * 3.不能够移除权限相同或者权限高于自己的团队成员
     *
     * @param teamMember:团队成员对象
     * @retuen boolean
     */
    public boolean delMember(TeamMember teamMember);

    /**
     * @describle 团队项目的创建
     * 1.任何人都可以创建项目
     * 2.
     *
     * @param teamProject 团队项目对象
     * @return boolean
     */
    public boolean createProject(TeamProject teamProject);

    /**
     *@describle 项目的删除
     *1.操作用户只能是创建者
     * @param teamProject
     * @param delBy
     *
     * @return
     */
    public boolean delProject(TeamProject teamProject,String delBy);


    /**
     *@describle 项目信息的更改
     *1.项目内的成员均可以操作
     * @param teamProject
     * @param updateBy
     *
     * @return
     */
    public boolean updateaProject(TeamProject teamProject,String updateBy);
    /**
     *@describle 项目成员的添加
     * 1.只能是当前团队的成员
     * 2.任何人可以操作
     * @param projectMember
     * @param inviteBy
     *
     * @return
     */
    public boolean addProjectMember(ProjectMember projectMember,String inviteBy);
    /**
     *@describle 项目成员的删除
     *1.只能够是项目的创建者来操作
     * @param
     * @param
     *
     * @return
     */
    public boolean delProjectMember(ProjectMember projectMember,String delBy);
    /**
     *@describle 团队公告的创建
     * 1.管理员或者负责人
     * @param
     * @param teamNotice
     *
     * @return boolean
     */
    public boolean createNotice(TeamNotice teamNotice);
    /**
     *@describle 更新公告
     * @param teamNotice
     * @return
     */
    public boolean updateNotice(TeamNotice teamNotice);
    /**
     *@describle 删除公告
     * @param teamNotice
     * @return
     */
    public boolean delNotice(TeamNotice teamNotice);
    /**
     *@describle 上传资料
     * @param file
     * @param
     * @return
     */
    public boolean putFile(File file);
    /**
     *@describle 更改文件资料
     * @param file
     *
     * @return
     */
    public boolean updateFile(File file);
    /**
     *@describle 删除文件资料
     *
     * @param file
     * @param delBy
     * @return
     */
    public boolean delFile(File file,String delBy);
}
