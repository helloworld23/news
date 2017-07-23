package com.news.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zhongziming on 2017/5/6.
 */
@Entity
public class SystemInfo {
    private int id;
    private String content;
    private String createTime2;
    private String lastModifyTime2;
    private Date createTime;
    private Date lastModifyTime;
    public SystemInfo(){}
    public SystemInfo(int id,String content,Date createTime,Date lastModifyTime){
        this.id = id ;
        this.content = content;
        this.createTime = createTime;
        this.lastModifyTime = lastModifyTime;
    }
    public void setCreateTime2(String createTime2) {
        this.createTime2 = createTime2;
    }

    public void setLastModifyTime2(String lastModifyTime2) {
        this.lastModifyTime2 = lastModifyTime2;
    }
    @Transient
    public String getCreateTime2() {

        return createTime2;
    }

    @Transient
    public String getLastModifyTime2() {
        return lastModifyTime2;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Date getLastModifyTime() {

        return lastModifyTime;
    }
}
