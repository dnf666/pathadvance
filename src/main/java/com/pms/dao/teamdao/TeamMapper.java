package com.pms.dao.teamdao;
import com.pms.model.team.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liudong on 2017/8/10.
 *
 *                                                         关于团队项目和团队文件还没有写
 */

//此注解 能将其注册为Spring Bean
// 能将所标注的类抛出的数据访问异常封装为Spring的数据访问异常，使得异常独立于底层框架
@Repository
public interface TeamMapper {
    /**
     * 创建团队
     * @param team 团队 team需要设值的参数：teamName , createBy , createTime
     * @return boolean
     */
    boolean addTeam(Team team);

    /**
     * 注销团队
     * @param team 团队 team需要设值的参数：teamName , delFlag , delTime , delRemarks
     * @return boolean
     */
    boolean delTeam(Team team);

    /**
     * 通过团队的名称得到团队的相关信息
     * @param teamName 团队名称
     * @return String
     */
    Team getTeamInfo(String teamName);

    /**
     * 得到所有团队对象
     * @return List
     */
    List<Team> getAllTeam();

    /**
     * 添加团队成员
     * @param teamMember 团队成员
     *         teamMember需要设值的参数：userName , teamName , teamRole , joinTime , joinBy , teamPrivelege
     * @return boolean
     */

    boolean addTeamMember(TeamMember teamMember);

    /**
     * 删除团队成员
     * @param teamMember 团队成员 teamMember需要设值的参数：delFlag , delTime , delRemarks ,teamName ,userName
     * @return boolean
     */
    boolean delTeamMember(TeamMember teamMember);

    /**
     * //通过团队名称获得该团队成员
     * @param teamName 团队名称
     * @return List
     */
    List<TeamMember> getTeamMembersByTeamName(String teamName);

    /**
     * 通过用户名来获取用户加入了哪些团队
     * @param userName 用户名称
     * @return List
     */
    List<TeamMember> getTeamInfoByUserName(String userName);
    /**
     * 创建公告
     * @param teamNotice 团队公告 teamNotice需要设值的参数： title , createBy , createTime , context , teamName ,
     * @return addNotice
     */
    boolean addNotice(TeamNotice teamNotice);

    /**
     * 删除公告
     * @param teamNotice 团队公告 teamNotice需要设值的参数：id (团队公告的id) ， delFlag ,delTime
     * @return boolean
     */
    boolean delNotice(TeamNotice teamNotice);

    /**
     * 更新公告
     * @param teamNotice 团队公告 teamNotice需要设值的参数：id (团队公告的id) ， title , createBy , createTime , context
     * @return boolean
     */
    boolean updateNotice(TeamNotice teamNotice);

    /**
     * 通过id获取准确的公告信息
     * @param id 公告的id
     * @return TeamNotice
     */
    TeamNotice getNoticeById(int id);

    /**
     * 获得该团队的所有公告
     * @param teamName 团队名称
     * @return List
     */
    List<TeamNotice> getNoticeByteamName(String teamName);

    /**
     * 设置权限
     * @param teamMember 团队成员  teamMember需要设值的参数： team_privelige , teamName , userName.
     * @return boolean
     */
    boolean setPrivilege(TeamMember teamMember);
    /**
     * 团队成员职务变动记录,
     * 只有更改了默认的职务才会在该表中添加信息,
     * 同时团队成员表中的信息也会更改。
     * @param teamMasterHistory 团队成员职务变化历史纪录
     *        teamMasterHistory需要设值的参数：teamName , userName , toRole , fromRole , modifyBy , modifyAr
     * @return boolean
     */
    boolean insertTeamMasterHistory(TeamMasterHistory teamMasterHistory);
}
