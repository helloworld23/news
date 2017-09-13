package com.news.service;

import com.news.dao.SystemInfoDAO;
import com.news.model.SystemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zhongziming on 2017/5/6.
 */
@Service
public class SystemInfoService {
    private SystemInfoDAO systemInfoDAO = null;

    public SystemInfoDAO getSystemInfoDAO() {
        return systemInfoDAO;
    }
    @Autowired
    public void setSystemInfoDAO(SystemInfoDAO systemInfoDAO) {
        this.systemInfoDAO = systemInfoDAO;
    }

    public boolean updateSystemInfo(int id,String createTime,String content){
        String now = getDate();
        SystemInfo systemInfo = new SystemInfo(id,content,now,now);
        return systemInfoDAO.updateSystenInfo(systemInfo);
    }

    public String getDate() {
        Date now = new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(now);
    }

    public List<SystemInfo> getSystemInfos(int start,int length){
        List<SystemInfo> systemInfos = systemInfoDAO.getSystemInfos(start,length);
        return systemInfos;
    }

    public boolean addSystemInfo(SystemInfo systemInfo){
        String now = getDate();
        systemInfo.setCreateTime(now);
        systemInfo.setLastModifyTime(now);
        boolean flag = systemInfoDAO.addSystemInfo(systemInfo);
        return flag;
    }

    public boolean deleteSystemInfo(SystemInfo systemInfo){
        return systemInfoDAO.deleteSystemInfos(systemInfo);
    }

    public int getSystemInfoCount(){
        return systemInfoDAO.getSystemInfoCount();
    }
}
