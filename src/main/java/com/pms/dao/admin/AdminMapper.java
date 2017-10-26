package com.pms.dao.admin;

import com.pms.model.admin.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {
    /**
     * 根据传入的 管理员账号来获取管理员的信息
     * @param adminName
     * @return
     */
    public Admin getAdminByAdminName(String adminName);

    /**
     * 更新管理员密码
     *  注：只能更新真实名称，不能更新用户名
     * @param admin
     * @return
     */
    public int updateAdminInfo(Admin admin);

}
