package com.news.service;

import com.news.dao.AdminDAO;
import com.news.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhongziming on 2017/5/5.
 */
@Service
public class AdminService {
    private AdminDAO adminDAO = null;

    public AdminDAO getAdminDAO() {
        return adminDAO;
    }
    @Autowired
    public void setAdminDAO(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    public boolean checkAdmin(String email,String password){
        Admin admin = adminDAO.getAdmin(email);
        if(admin != null && admin.getPassword().equals(password)) return true;
        return false;
    }

    public Admin getAdminByEmail(String email){
        return adminDAO.getAdminByEmail(email);
    }

    public boolean updateAdmin(Admin admin){
        return adminDAO.updateAdmin(admin);
    }
}
