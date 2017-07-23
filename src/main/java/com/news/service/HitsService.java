package com.news.service;

import com.news.dao.HitsDAO;
import com.news.model.Hits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhongziming on 2017/5/18.
 *
 * */
@Service
public class HitsService {
    private HitsDAO hitsDAO = null;
    @Autowired
    public void setHitsDAO(HitsDAO hitsDAO){
        this.hitsDAO = hitsDAO;
    }
    public HitsDAO getHitsDAO(){
        return this.hitsDAO;
    }
    public boolean deleteHits(Hits hits){
       return  hitsDAO.deleteHits(hits);
    }
    public boolean updateHits(Hits hits) { return  hitsDAO.updateHits(hits); }
    public List<Hits> getHits(){return hitsDAO.getHits();}
}
