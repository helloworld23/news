package com.news.dao;

import com.news.model.SystemInfo;
import com.news.utils.SessionUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhongziming on 2017/5/6.
 */
@Component
public class SystemInfoDAO {
    private SessionUtils sessionUtils = null;

    public SessionUtils getSessionUtils() {
        return sessionUtils;
    }
    @Autowired
    public void setSessionUtils(SessionUtils sessionUtils) {
        this.sessionUtils = sessionUtils;
    }

    public boolean updateSystenInfo(SystemInfo systemInfo){
        Session session = null;
        try{
            session = sessionUtils.getSession();
            session.saveOrUpdate(systemInfo);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteSystemInfos(SystemInfo systemInfo){
        Session session = null;
        try{
            session = sessionUtils.getSession();
            session.delete(systemInfo);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<SystemInfo> getSystemInfos(int start ,int length){
        Session session = null;
        List<SystemInfo> systemInfos = null;
        try{
            session = sessionUtils.getSession();
            String sql = "from SystemInfo systemInfo where 1=1";
            Query query = session.createQuery(sql);
            query.setMaxResults(length);
            query.setFirstResult(start);
            systemInfos = query.list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return systemInfos;
    }
    public boolean addSystemInfo(SystemInfo systemInfo){
        Session session = null;
        List<SystemInfo> systemInfos = null;
        try{
            session = sessionUtils.getSession();
            session.save(systemInfo);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getSystemInfoCount(){
        Session session = null;
        Number length = null;
        String sql = "select count(id) from SystemInfo where 1=1";
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
}
