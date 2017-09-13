package com.news.dao;

import com.news.model.User;
import com.news.utils.SessionUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhongziming on 2017/5/5.
 */

@Repository
public class UserDAO {
    private SessionUtils sessionUtils = null;

    public SessionUtils getSessionUtils() {
        return sessionUtils;
    }
    @Autowired
    public void setSessionUtils(SessionUtils sessionUtils) {
        this.sessionUtils = sessionUtils;
    }

    public List<User> getUser(int start,int length){
        List<User> users  = null;
        Session session = null;
        try{
            session = sessionUtils.getSession();
            String sql = "from User as user where 1 = 1";
            Query query = session.createQuery(sql);
            query.setFirstResult(start);
            query.setMaxResults(length);
            users = query.list();
        }catch (Exception e){
            e.printStackTrace();
        }

        return users;
    }

    public User getUserById(int id){
        Session session = null;
        User user = null;
        try{
            session = sessionUtils.getSession();
            user = (User) session.get(User.class ,id);
        }catch (Exception e){
            e.printStackTrace();
        }

        return user;
    }
    public User getUserByEmail(String email){
        Session session = null;
        User user = null;
        try{
        session = sessionUtils.getSession();
        String sql = "from User as user where user.email = :email";
        Query query = session.createQuery(sql);
        query.setString("email",email);
        List<User> users = query.list();
        if(users != null){
            user = users.get(0);
        }
        }catch (Exception e){
            e.printStackTrace();
        }

        return user;
    }

    public boolean updateUser(User user){
        Session session = null;
        try{
            session = sessionUtils.getSession();
            session.update(user);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return  true;

    }

    public boolean addUser(User user){
        Session session = null;
        try{
            session = sessionUtils.getSession();
            session.save(user);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getUserCount(){
        Session session = null;
        Number length = null;
        String sql = "select count(id) from User where 1=1";
        try {
            session = sessionUtils.getSession();
            Query query = session.createQuery(sql);
            List<Object> list = query.list();
            length= (Number) list.get(0);

        }catch (Exception e){
            e.printStackTrace();
        }
        return length.intValue();
    }

    public boolean deleteUser(User user){
        Session session = null;
        try {
            session = sessionUtils.getSession();
            session.delete(user);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
