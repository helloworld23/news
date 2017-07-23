package com.news.controller;

import com.news.model.Admin;
import com.news.model.User;
import com.news.service.AdminService;
import com.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by zhongziming on 2017/5/5.
 */
@Controller
@RequestMapping("login")
public class LoginController{
    private UserService userService = null;
    private AdminService adminService = null;

    public AdminService getAdminService() {
        return adminService;
    }
    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    public UserService getUserService() {
        return userService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "userCheck")
    public String userCheck(User user, HttpSession session){
        boolean flag = userService.checkUser(user.getEmail(),user.getPassword());
        if (flag == true){
            user = userService.getUser(user.getEmail());
            session.setAttribute("user",user);
            return "redirect:/news/publish";
        }
        return "redirect:/error";
    }
    @RequestMapping(value = "adminCheck")
    public String adminCheck(Admin admin,HttpSession session){
        String email = admin.getEmail();
        String password = admin.getPassword();
        boolean flag = adminService.checkAdmin(email,password);
        if(flag == true){
            admin = adminService.getAdminByEmail(email);
            session.setAttribute("admin",admin);
            return "redirect:/admin/systemInfo";
        }
        return "redirect:/error";
    }

    @RequestMapping("loginToIndex")
    public String loginToIndex(User user,HttpSession session){
        boolean flag = userService.checkUser(user.getEmail(),user.getPassword());
        if (flag == true){
            user = userService.getUser(user.getEmail());
            session.setAttribute("user",user);
            return "redirect:/index";
        }
        return "redirect:/error";
    }
}
