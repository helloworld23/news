package com.news.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by zhongziming on 2017/5/5.
 */
@Component
public class SessionUtils {

    private SessionFactory sessionFactory = null;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }
}
