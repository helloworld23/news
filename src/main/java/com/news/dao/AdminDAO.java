package com.news.dao;

import com.news.model.Admin;
import com.news.model.User;
import com.news.utils.SessionUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhongziming on 2017/5/5.
 */
@Repository
public class AdminDAO {
    private SessionUtils sessionUtils = null;

    public SessionUtils getSessionUtils() {
        return sessionUtils;
    }
    @Autowired
    public void setSessionUtils(SessionUtils sessionUtils) {
        this.sessionUtils = sessionUtils;
    }

    public Admin getAdmin(String email){
        Session session = null;
        Admin admin = null;
        try{
            session = sessionUtils.getSession();
            String sql = "from Admin as admin where admin.email = :email";
            Query query = session.createQuery(sql);
            query.setString("email",email);
            List<Admin> admins = query.list();
            if(admins != null){
                admin = admins.get(0);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return admin;
    }
    public Admin getAdminByEmail(String email){
        Session session = null;
        Admin admin = null;
        String sql = "from Admin as admin where admin.email = :email";
        try{
            session = sessionUtils.getSession();
            Query query = session.createQuery(sql);
            query.setString("email",email);
            List<Admin> admins = query.list();
            admin = admins.get(0);
        }catch (Exception e){
            e.printStackTrace();
        }

        return admin;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean updateAdmin(Admin admin){
        Session session = null;
        try{
            session = sessionUtils.getSession();
            session.getTransaction().begin();
            session.update(admin);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        session.getTransaction().commit();
        return true;
    }
}
