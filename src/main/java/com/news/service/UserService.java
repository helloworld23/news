package com.news.service;

import com.news.dao.UserDAO;
import com.news.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhongziming on 2017/5/5.
 */
@Service
public class UserService {
    private UserDAO userDAO = null;
    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserDAO getUserDAO() {

        return userDAO;
    }

    public boolean checkUser(String email,String password){
        User user = userDAO.getUserByEmail(email);
        if(user!=null &&user.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    public User getUser(String email){
        User user = userDAO.getUserByEmail(email);
        return user;
    }

    public User getUserById(int id){
        User user = userDAO.getUserById(id);
        return user;
    }

    public List<User> getUser(int start ,int length){
        List<User> users = userDAO.getUser(start,length);
        return users;
    }

    public boolean updateUser(User user){
        boolean flag = userDAO.updateUser(user);
        return flag;
    }

    public boolean addUser(User user){
        boolean flag = userDAO.addUser(user);
        return flag;
    }

    public int getUserCount(){
        return userDAO.getUserCount();
    }

    public boolean deleteUser(User user){
        return userDAO.deleteUser(user);
    }

}
