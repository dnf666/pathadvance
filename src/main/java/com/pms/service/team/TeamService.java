
package com.pms.service.team;
import com.pms.model.file.FileImpl;
import com.pms.model.project.Project;
import com.pms.model.project.ProjectMember;
import com.pms.model.team.*;
import java.util.List;
/**
 * Created by liudong on 2017/8/15.
 * @author liudong
 *
 */
public interface TeamService {
    /**
     * 判断当前成员是否是当前的项目成员
     *      （思路是得到项目成员列表，
     *       让用户名与项目成员列表中的成员的名称做对比，
     *       如果有相同的表示是当前项目的成员）
     * @param list 项目成员列表
     * @param userName 用户名
     * @return boolean
     */
    boolean isProMember(List<ProjectMember> list, String userName);
    /**
     * 得到名称为username的用户在当前团队的相关信息
     * @param  list 团队成员列表
     * @param userName 用户名
     * @return TeamMember
     */
    TeamMember getTeamMember(List<TeamMember> list,String userName);
    /**
     * 通过团队名称和用户名获取团队成员
     * @param teamName 团队名称
     * @param userName 用户名
     * @return teammember
     */
    TeamMember getTeamMemberByTeamNameAndUserName(String teamName , String userName);

    /**
     * 获取已经被删除的团队成员
     * @param teamName 团队名称
     * @param userName 用户名称
     * @return teammember
     */
    TeamMember getDelTeamMember(String teamName , String userName);
    /**
     * 得到用户在团队当中的权限。
     * @param teamMember 团队成员
     * @return int
     */
    int getTeamPrivilege(TeamMember teamMember);
    /**
     * 创建一个新的团队， 默认创建团队者为最高权限者。
     * @param team 团队对象
     *  @return boolean
     */
    boolean createTeam(Team team);
    /**
     * 得到所有团队的信息
     * @return List
     */
    List<Team> getAllTeam();
    /**
     * 通过用户名获该成员的所在团队
     * @param userName 用户名
     * @return List
     */
    List<Team> getMyteam(String userName);

    /**
     * 通过用户名获得用户可以管理的团队
     * @param userName 用户名
     * @return list
     */
    List<Team> getManagedTeam(String userName);
    /**
     * 获得用户加入但没有管理权限的团队
     * @param userName 用户名
     * @return list
     */
    List<Team> getJoinTeam(String userName);
    /**
     *团队注销，只能够由团队负责人注销
     * @param team 团队
     * @param delBy 删除者
     * @return boolean
     */
    boolean delTeam(Team team ,String delBy);

    /**
     * 邀请团队成员
     * @param teamMember 团队成员
     * @return boolean
     */
    boolean inviteMember(TeamMember teamMember);
    /**
     * 根据团队名称获取团队成员
     * @param teamName 团队名称
     * @return List
     */
    List<TeamMember> getTeamMembers(String teamName);
    /**
     *移除团队成员
     * 1.有一定的权限才能够移除
     * 2.自己不能够移除自己，就是自己不能够主动推出当前团队
     * 3.不能够移除权限相同或者权限高于自己的团队成员
     * @param teamMember:团队成员对象
     * @return boolean
     */
    boolean delTeamMember(TeamMember teamMember);
    /**
     * 团队项目的创建，项目必须依附于某个团队
     * @param teamProject 团队项目对象
     * @return boolean
     */
    boolean createProject(Project teamProject);
    /**
     *项目的删除
     *1.操作用户只能是创建者
     * @param teamProject 团队项目
     * @param delBy 删除者
     * @exception Exception 调用项目接口方法抛出的异常
     * @return boolean
     */
    boolean delProject(Project teamProject,String delBy) throws Exception;
    /**
     * 设置成员权限，只有负责人才能够设置
     * @param teamPrivilige 团队成员
     * @return boolean
     */
    boolean setTeamPrivilige(TeamMember teamPrivilige);
    /**
     * 通过团队名称得到团队中的所有项目
     * @param teamName 团队名称
     * @return List
     */
    List<Project> getTeamProjectsByTeamName(String teamName);

    /**
     * 通过项目id得到项目信息
     * @param projectId 项目id
     * @return project
     */
    Project getProjectInfo(int projectId);
    /**
     * 通过团队名称和项目名称后去项目成员
     * @param projectId 项目id
     * @return List
     */
    List<ProjectMember> getProMemberByProID(int projectId);
    /**
     *项目信息的更改
     *1.项目内的成员均可以操作
     * @param teamProject 团队项目
     * @param updateBy 更新者
     * @exception Exception 抛出接口方面的异常
     * @return boolean
     */
    boolean updateaProject(Project teamProject,String updateBy) throws Exception;
    /**
     *项目成员的添加
     * 1.只能是当前团队的成员
     * 2.任何人可以操作
     * @param projectMember 项目成员
     *
     * @return boolean
     */
    boolean addProjectMember(ProjectMember projectMember);
    /**
     *项目成员的删除
     * @param projectMember 要删除的项目成员对象
     * @return boolean
     */
    boolean delProjectMember(ProjectMember projectMember);
    /**
     *团队公告的创建
     * 1.管理员或者负责人
     * @param teamNotice 团队公告
     * @return boolean
     */
    boolean createNotice(TeamNotice teamNotice);
    /**
     *更新者为管理员或者负责人时更新公告
     * @param teamNotice 团队公告
     * @param updateBy 更新者
     * @return boolean
     */
    boolean updateNotice(TeamNotice teamNotice,String updateBy);
    /**
     * 删除者为管理员或者负责人时删除公告
     * @param teamNotice 团队公告
     * @param delBy 删除者
     * @return boolean
     */
    boolean delNotice(TeamNotice teamNotice,String delBy);
    /**
     * 通过团队名称得到团队的所有公告信息
     * @param teamName 团队名称
     * @return boolean
     */
    List<TeamNotice> getTeamNotice(String teamName);

    /** 
     * 通过公告id获取公告
     * @param noticeId 公告id
     * @return TeamNotice
     */
    TeamNotice getNoticeById(int noticeId);
    /**
     * 修改团队成员的角色,通过形参传入的信息，保存到teamMasterHistory对象的信息中，然后将对象中的信息插入这个历史记录表
     * @param teamMasterHistory 团队成员
     * @return boolean
     */
    boolean updateTeamRole(TeamMasterHistory teamMasterHistory);
    //关于团队文件，注释没有写好
    // 还没有弄好，先把团队除了文件弄好，然后弄文件，最后处理团队其他内容
    /**
     * 上传团队文件
     * @param file 文件对象
     * @param teamId 团队id即将文件上传到的团队id
     * @return boolean
     */
    boolean addTeamFile(FileImpl file, int teamId);

    /**
     * 根据文件的id将文件删除，只有文件的上传者才能够删除
     * @param fileId 文件的id
     * @param delBy 文件删除者
     * @return boolean
     */
    boolean delTeamFileById(FileImpl fileImpl, int fileId, String delBy);

    /**
     * 通过文件的id下载文件
     * @param fileId 文件名
     * @return boolean
     */
    boolean downloadTeamFileById(int fileId);

    /**
     * 根据团队id展示团队文件
     * @param teamId 团队id
     * @return 文件对象的列表
     */
    List<FileImpl> showTeamFiles(int teamId);
}