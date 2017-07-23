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
        SystemInfo systemInfo = new SystemInfo(id,content,new Date(Long.parseLong(createTime)),new Date());
        return systemInfoDAO.updateSystenInfo(systemInfo);
    }
    public List<SystemInfo> getSystemInfos(int start,int length){
        List<SystemInfo> systemInfos = systemInfoDAO.getSystemInfos(start,length);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (SystemInfo systemInfo:systemInfos){
            Date createTime = systemInfo.getCreateTime();
            Date lastModifyTime = systemInfo.getLastModifyTime();
            if(createTime != null){
                String createTime2 = format.format(createTime);
                systemInfo.setCreateTime2(createTime2);
            }
            if(lastModifyTime != null){
                String lastModifyTime2 = format.format(lastModifyTime);
                systemInfo.setLastModifyTime2(lastModifyTime2);
            }
        }
        return systemInfos;
    }

    public boolean addSystemInfo(SystemInfo systemInfo){
        Date date = new Date();
        systemInfo.setCreateTime(date);
        systemInfo.setLastModifyTime(date);
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
