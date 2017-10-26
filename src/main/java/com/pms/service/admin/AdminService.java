package com.pms.service.admin;

import com.pms.dao.admin.AdminMapper;
import com.pms.model.admin.Admin;
import com.pms.model.exception.WrongPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    AdminMapper adminMapper;
    public boolean login(Admin admin){
        Admin admin_db = adminMapper.getAdminByAdminName(admin.getAdminName());
        if (admin_db == null) {
            throw new NullPointerException("用户名 " + admin.getAdminName() +" 不存在");
        }

        if (!admin_db.getPassword().equals(admin.getPassword())){
            throw new WrongPasswordException(admin.getAdminName());
        }
        return true;
    }

    private boolean modifyInfo(Admin admin){
        Admin admin_db = adminMapper.getAdminByAdminName(admin.getAdminName());
        if (admin_db == null){
            throw new NullPointerException("用户名 "+ admin.getAdminName() + " 不存在");
        }

        return (adminMapper.updateAdminInfo(admin)>0)? true:false;
    }

    public boolean resetPassword(String adminName){
        Admin admin = adminMapper.getAdminByAdminName(adminName);
        final String password = "123456";
        admin.setPassword(password);
        admin.setRealName(null);
        return modifyInfo(admin);
    }

    public boolean modifyPassword(Admin admin){
        admin.setRealName(null);
        return modifyInfo(admin);
    }

    public boolean modifyRealName(Admin admin){
        admin.setPassword(null);
        return modifyInfo(admin);
    }
}
