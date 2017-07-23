package com.news.dao;

import com.news.model.Article;
import com.news.utils.SessionUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhongziming on 2017/5/8.
 */
@Component
public class ArticleDAO {
    private SessionUtils sessionUtils = null;

    public SessionUtils getSessionUtils() {
        return sessionUtils;
    }
    @Autowired
    public void setSessionUtils(SessionUtils sessionUtils) {
        this.sessionUtils = sessionUtils;
    }
    public boolean addArticle(Article article){
        Session session = null;
        try{
            session = sessionUtils.getSession();
            session.save(article);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public Article getArticleById(int id){
        Session session = null;
        Article article = null;
        List<Article> articles = null;
        Article article1 = null;
        String sql = "from Article as article where article.id = :id";
        try{
            session = sessionUtils.getSession();
            Query query = session.createQuery(sql);
            query.setInteger("id",id);
            articles = query.list();
            if (articles != null){
                article = articles.get(0);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return article;
    }
    public Article getArticleByIdToRead (int id){
        Session session = null;
        Article article = null;
        List<Article> articles = null;
        Article article1 = null;
        String sql = "from Article as article where article.id = :id and status = 1";
        try{
            session = sessionUtils.getSession();
            Query query = session.createQuery(sql);
            query.setInteger("id",id);
            articles = query.list();
            if (articles != null){
                article = articles.get(0);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return article;
    }

    //返回状态为2即未审核的文章列表
    public int getReviewArticleCount(){
        Session session = null;
        Number length = null;
        String sql = "select count(article.id) from Article article where article.status =2";
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

    public List<Article> getArticleByAuthor(int start,int length,String author){
        List<Article> articles = null;
        Session session = null;
        String sql = "from Article as article where article.author =:author";
        try{
            session = sessionUtils.getSession();
            Query query = session.createQuery(sql);
            query.setString("author",author);
            query.setFirstResult(start);
            query.setMaxResults(length);
            articles = query.list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return articles;
    }
    //返回用户文章数量
    public int getAuthorArticleCount(String author){
        Session session = null;
        Number length = null;
        String sql = "select count(article.id) from Article article where article.author = :author";
        try {
            session = sessionUtils.getSession();
            Query query = session.createQuery(sql);
            query.setString("author",author);
            List<Object> list = query.list();
            length= (Number) list.get(0);

        }catch (Exception e){
            e.printStackTrace();
        }
        return length.intValue();
    }
    //获得需要审核的新闻
    public List<Article> getArticleByStatus(int start ,int length){
        List<Article> articles =null;
        Session session = null;
        String sql = "from Article as article where article.status = 2";
        try{
            session = sessionUtils.getSession();
            Query query = session.createQuery(sql);
            query.setFirstResult(start);
            query.setMaxResults(length);
            articles = query.list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return articles;
    }
    public boolean updateArticle(Article article){
        Session session = null;
        try {
            session = sessionUtils.getSession();
            session.update(article);

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return  true;
    }
    public boolean updateStatus(int id,int status){
        String sql = "update Article a set a.status =:status where a.id = :id";
        Session session = null;
        try {
            session = sessionUtils.getSession();
            Query query = session.createQuery(sql);
            query.setInteger("status",status);
            query.setInteger("id",id);
            query.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteArticle(Article article){
        Session session = null;
        try {
            session = sessionUtils.getSession();
            session.delete(article);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Article> getArticleByKeyword(int start,int length,String keyword){
        List<Article> articles =null;
        Session session = null;
        String sql = "from Article as article where article.author like :author or article.title like :title";
        try{
            session = sessionUtils.getSession();
            Query query = session.createQuery(sql);
            query.setString("author","%"+keyword+"%");
            query.setString("title","%"+keyword+"%");
            query.setFirstResult(start);
            query.setMaxResults(length);
            articles = query.list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return articles;
    }
    public int getKeywordArticleCount(String keyword){
        Session session = null;
        Number length = null;
        String sql = "select count(article.id) from Article as article where article.author like :author or article.title like :title";
        try {
            session = sessionUtils.getSession();
            Query query = session.createQuery(sql);

            query.setString("author","%"+keyword+"%");
            query.setString("title","%"+keyword+"%");
            List<Object> list = query.list();
            length= (Number) list.get(0);
        }catch (Exception e){
            e.printStackTrace();
        }
        return length.intValue();
    }
}
