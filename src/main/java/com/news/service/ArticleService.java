package com.news.service;

import com.news.dao.ArticleDAO;
import com.news.model.Article;
import com.news.model.Comment;
import com.news.model.Hits;
import com.news.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by zhongziming on 2017/5/8.
 */
@Service
public class ArticleService {
    private ArticleDAO articleDAO = null;
    private UserService userService = null;
    private HitsService hitsService = null;

    @Autowired
    public void setHitsService(HitsService hitsService) {
        this.hitsService = hitsService;
    }

    public HitsService getHitsService() {
        return hitsService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }

    public ArticleDAO getArticleDAO() {
        return articleDAO;
    }

    @Autowired
    public void setArticleDAO(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    //新增新闻
    public boolean addArticle(Article article, String userName) {
        Date createTime = getDate();
        article.setAuthor(userName);
        article.setCreateTime(createTime);
        article.setLastModifyTime(createTime);//创建时间也是初始的最后修改时间
        Hits hits = new Hits();
        hits.setArticle(article);
        article.setHits(hits);//设置初始热度
        boolean flag = articleDAO.addArticle(article);
        return flag;
    }

    public boolean addComment(int userId, int articleId, String content) {
        User user = userService.getUserById(userId);
        Article article = articleDAO.getArticleById(articleId);
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCreateTime(getDate());
        comment.setUser(user);
        comment.setArticle(article);
        article.getComments().add(comment);
        return articleDAO.updateArticle(article);
    }

    public int getReviewArticleCount() {
        int count = articleDAO.getReviewArticleCount();
        return count;
    }

    public List<Article> getArticleByStatus(int start, int length) {
        List<Article> articles = articleDAO.getArticleByStatus(start, length);
        return articles;
    }

    public List<Article> getArticleByAuthor(int start, int length, String author) {
        List<Article> articles = articleDAO.getArticleByAuthor(start, length, author);
        return articles;
    }

    public int getAuthorArticleCount(String author) {
        return articleDAO.getAuthorArticleCount(author);
    }

    //按ID取文章，并且同时增加热度
    public Article getArticleById(int id) {
        Article article = articleDAO.getArticleById(id);
        if (article != null) {
            Hits hit = article.getHits();
            int rate = hit.getRate();
            hit.setRate(rate + 1);
            hit.setArticle(article);
            hitsService.updateHits(hit);
        }
        return article;
    }
    public Article getArticleByIdToRead(int id) {
        Article article = articleDAO.getArticleByIdToRead(id);
        if (article != null) {
            Hits hit = article.getHits();
            int rate = hit.getRate();
            hit.setRate(rate + 1);
            hit.setArticle(article);
            hitsService.updateHits(hit);
        }
        return article;
    }

    public boolean updateStatus(String id, String status) {
        boolean flag = articleDAO.updateStatus(Integer.parseInt(id), Integer.parseInt(status));
        return flag;
    }

    public boolean updateArticle(int id, String title, String content, String category) {
        Article article = articleDAO.getArticleById(id);
        article.setTitle(title);
        article.setContent(content);
        article.setCategory(category);
        Date lastModifyTime = getDate();
        article.setLastModifyTime(lastModifyTime);
        boolean flag = articleDAO.updateArticle(article);
        return flag;
    }

    public boolean deleteArticle(int id) {
        Article article = articleDAO.getArticleById(id);
        Hits hits = article.getHits();
        hits.setArticle(null);
        List<Comment> comments = article.getComments();
        for (Comment comment : comments) {
            comment.setArticle(null);
        }
        boolean flag = articleDAO.deleteArticle(article);
        return flag;
    }

    public List<Article> getArticleByKeyword(int start, int length, String keyword) {
        return articleDAO.getArticleByKeyword(start, length, keyword);
    }

    public int getKeywordArticleCount(String keyword) {
        return articleDAO.getKeywordArticleCount(keyword);
    }

    public Date getDate() {
        return new Date();
    }
}
