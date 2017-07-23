package com.news.controller;

import com.news.model.SystemInfo;
import com.news.model.User;
import com.news.service.SystemInfoService;
import com.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhongziming on 2017/5/6.
 */
@Controller
@RequestMapping("user")
public class UserController {
    private SystemInfoService systemInfoService = null;
    private UserService userService = null;

    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public SystemInfoService getSystemInfoService() {
        return systemInfoService;
    }

    @Autowired
    public void setSystemInfoService(SystemInfoService systemInfoService) {
        this.systemInfoService = systemInfoService;
    }

    @RequestMapping("systemInfo")
    public ModelAndView systemInfo() {
        ModelAndView model = new ModelAndView();
        List<SystemInfo> systemInfos = systemInfoService.getSystemInfos(0,10);
        model.addObject("systemInfos", systemInfos);
        model.setViewName("systemInfo");
        return model;
    }

    @RequestMapping("message")
    public String message(HttpSession session) {
        User user = (User) session.getAttribute("user");
        session.setAttribute("user",userService.getUser(user.getEmail()));
        return "userMessage";
    }
    @RequestMapping("updateUser")
    @ResponseBody
    public Map<String,Object> update(User user,HttpSession session){
        Map<String,Object> map = new HashMap<>();
        boolean flag = userService.updateUser(user);
        if (flag == true){
            session.setAttribute("user",user);
            map.put("msg","更新成功");
        }else{
            map.put("msg","更新失败");
        }
        return map;
    }

    @RequestMapping("deleteUser")
    @ResponseBody
    public Map<String,String> delete(User user){
        Map<String ,String> map = new HashMap<>();
        boolean flag = userService.deleteUser(user);
        if(flag == true){
            map.put("msg","删除成功");
        }else{
            map.put("msg","删除失败");
        }
        return map;
    }


    @RequestMapping("add")
    public String add(User user,HttpSession session){
        boolean flag = userService.addUser(user);
        if(flag == true){
            session.setAttribute("user",user);
        }
        return "redirect:systemInfo";
    }

    @RequestMapping("getUser")
    @ResponseBody
    public Map<String,Object> search(int draw , int start , int length){
        Map<String,Object> map = new HashMap<>();
        List<User> users =  userService.getUser(start,length);
        int count = userService.getUserCount();
        if(users != null){
            map.put("msg","获取用户成功");
            map.put("draw",draw);
            map.put("data",users);
            map.put("recordsTotal",count);
            map.put("recordsFiltered",count);
        }else{
            map.put("msg","获取用户失败");
        }
        return map;
    }

    @RequestMapping("search")
    public String search(){
        return "userList";
    }
    @RequestMapping("addPage")
    public String addPage(){
        return "sign";
    }
    @RequestMapping("error")
    public String error(){
        return "error";
    }

}