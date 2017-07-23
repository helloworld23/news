package com.news.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by zhongziming on 2017/5/5.
 * @describe 点击量类
 */
@Entity
public class Hits {
    private int id;
    private Article article;
    private int rate;//
    public Hits(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @OneToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getRate() {

        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
