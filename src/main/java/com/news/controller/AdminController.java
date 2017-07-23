package com.news.controller;

import com.news.model.Admin;
import com.news.model.SystemInfo;
import com.news.service.AdminService;
import com.news.service.SystemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by zhongziming on 2017/5/7.
 */
@Controller
@RequestMapping("admin")
public class AdminController {
    private SystemInfoService systemInfoService = null;
    private AdminService adminService = null;
    @Autowired
    public void setAdminService(AdminService adminService){
        this.adminService = adminService;
    }
    public AdminService getAdminService(){
        return this.adminService;
    }

    public SystemInfoService getSystemInfoService() {
        return systemInfoService;
    }
    @Autowired
    public void setSystemInfoService(SystemInfoService systemInfoService) {
        this.systemInfoService = systemInfoService;
    }

    @RequestMapping("systemInfo")
    public ModelAndView systemInfo(){
        ModelAndView model = new ModelAndView();
        List<SystemInfo> systemInfos = systemInfoService.getSystemInfos(0,10);
        model.addObject("systemInfos",systemInfos);
        model.setViewName("adminSystemInfo");
        return model;
    }

    @RequestMapping("getSystemInfos")
    @ResponseBody
    public Map<String,Object> getSystemInfos(int draw,int start ,int length){
        Map<String,Object> map = new HashMap<>();
        List<SystemInfo> systemInfos = systemInfoService.getSystemInfos(start,length);
        int count = systemInfoService.getSystemInfoCount();
        map.put("draw",draw);
        map.put("recordsTotal",count);
        map.put("recordsFiltered",count);
        map.put("data",systemInfos);
        return map;
    }
    @RequestMapping("updateSystemInfo")
    @ResponseBody
    public Map<String,Object> updateSystemInfo(int id,String content,String createTime){
        Map<String,Object> map = new HashMap<>();
        boolean flag = systemInfoService.updateSystemInfo(id,createTime,content);
        if(flag == true){
            map.put("msg","更新成功");
        }else{
            map.put("msg","更新失败");
        }
        return map;
    }

    @RequestMapping("addSystemInfo")
    @ResponseBody
    public Map<String,String> addSystemInfo(SystemInfo systemInfo){
        Map<String,String> map = new HashMap<>();
        boolean flag = systemInfoService.addSystemInfo(systemInfo);
        if(flag == true){
            map.put("msg","success");
        }else{
            map.put("msg","error");
        }
        return map;
    }
    @RequestMapping("deleteSystemInfo")
    @ResponseBody
    public Map<String,Object> deleteSystemInfo(SystemInfo systemInfo){
        Map<String,Object> map = new HashMap<>();
        boolean flag = systemInfoService.deleteSystemInfo(systemInfo);
        if(flag == true){
            map.put("msg","删除成功");
        }else{
            map.put("msg","删除失败");
        }
        return map;
    }

    @RequestMapping("updateAdmin")
    @ResponseBody
    public Map<String,String> updateAdmin(Admin admin){
        Map<String,String> map = new HashMap<>();
        boolean flag = adminService.updateAdmin(admin);
        if (flag == true){
            map.put("msg","更新管理员信息成功");
        }else{
            map.put("msg","更新管理员信息失败");
        }
        return  map;
    }

    @RequestMapping("search")
    public String searchAdmin(){
        return "adminMessage";
    }

}
