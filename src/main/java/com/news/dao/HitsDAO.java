package com.news.dao;

import com.news.model.Hits;
import com.news.utils.SessionUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhongziming on 2017/5/18.
 */
@Repository
public class HitsDAO {
    private SessionUtils sessionUtils = null;
    //private Session session = sessionUtils.getSession();//可能NullPointException 待测试
    @Autowired
    public void setSessionUtils(SessionUtils sessionUtils) {

        this.sessionUtils = sessionUtils;
    }
    public SessionUtils getSessionUtils(){

        return sessionUtils;
    }
    public boolean deleteHits(Hits hits){
        Session session = sessionUtils.getSession();
        try{
            session.delete(hits);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean updateHits(Hits hits){
        Session session = sessionUtils.getSession();
        try{
            session.update(hits);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Hits> getHits(){
        List<Hits> hits = null;
        Session session = null;
        String sql = "from Hits as hits order by hits.rate desc";
        try{
            session = sessionUtils.getSession();
            Query query = session.createQuery(sql);
            query.setMaxResults(5);
            hits = query.list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return hits;
    }
}
