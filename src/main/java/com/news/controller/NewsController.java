package com.news.controller;

import com.news.model.Article;
import com.news.model.User;
import com.news.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhongziming on 2017/5/6.
 */
@Controller
@RequestMapping("news")
public class NewsController {
    private ArticleService articleService = null;

    public ArticleService getArticleService() {
        return articleService;
    }
    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping("publish")
    public String publish(){
        return "newsPublish";
    }
    @RequestMapping("add")
    @ResponseBody
    public Map<String,String> addArticle(Article article, HttpSession session){
        Map<String,String> map = new HashMap<>();
        User user = (User) session.getAttribute("user");
        boolean flag = articleService.addArticle(article,user.getUserName());
        if(flag == true) map.put("msg","增加新闻成功");
        else map.put("msg","增加新闻失败");
        return map;
    }

    @RequestMapping("review")
    public String review(){
        return "ReviewNews";
    }

    @RequestMapping("getArticleToReview")
    @ResponseBody
    public Map<String,Object> getArticleToReview(int draw ,int start ,int length){
        Map<String,Object> map = new HashMap<>();
        List<Article> articles = articleService.getArticleByStatus(start,length);
        int count = articleService.getReviewArticleCount();
        map.put("draw",draw);
        map.put("recordsTotal",count);
        map.put("recordsFiltered",count);
        map.put("data",articles);
        return map;
    }
    @RequestMapping("getArticleById")
    public ModelAndView getArticleById(int id){
        ModelAndView model = new ModelAndView();
        Article article = articleService.getArticleById(id);
        if(article == null){
            model.addObject("msg","获取新闻失败");
        }else {
            model.addObject("msg","获取新闻成功");
            model.addObject("article",article);
            model.setViewName("article");
        }
        return model;
    }
    @RequestMapping("updateNewsStatus")
    @ResponseBody
    public Map<String,String> updateStatus(String id,String status){
        Map<String,String> map = new HashMap<>();
        boolean flag = articleService.updateStatus(id,status);
        if(flag == true){
            map.put("msg","修改状态成功");
        }else {
            map.put("msg","修改状态失败");
        }
        return map;
    }
    @RequestMapping("updateNewsPage")
    public ModelAndView updateNewsPage(int id)
    {
        ModelAndView model = new ModelAndView();
        Article article = articleService.getArticleById(id);
        model.addObject("article",article);
        model.setViewName("newsPublish");
        return model;
    }

    @RequestMapping("updateArticle")
    @ResponseBody
    public Map<String,String> updateArticle(int id,String title,String content,String category){
        Map<String ,String> map = new HashMap<>();
        boolean flag = articleService.updateArticle(id,title,content,category);
        if (flag == true){
            map.put("msg","更新新闻成功");
        }else {
            map.put("msg","更新新闻失败");
        }
        return map;
    }

    @RequestMapping("deleteArticle")
    @ResponseBody
    public Map<String ,String> deleteArticle(int id){
        Map<String,String> map = new HashMap<>();
        boolean flag = articleService.deleteArticle(id);
        if(flag == true){
            map.put("msg","删除新闻成功");
        }else {
            map.put("msg","删除新闻失败");
        }
        return map;
    }
    @RequestMapping("userNewsSearch")
    public String userNewsSearch(){
        return "userNewsSearch";
    }
    @RequestMapping("getArticleByKeyword")
    @ResponseBody
    public Map<String ,Object> getArticleByKeyword(int draw ,int start ,int length,String keyword){
        Map<String,Object> map = new HashMap<>();
        List<Article> articles = articleService.getArticleByKeyword(start,length,keyword);
        int count = articleService.getKeywordArticleCount(keyword);
        if(count == 0){
            map.put("msg","关键词下暂无文章");
        }else {
            map.put("msg","搜索完毕");
            map.put("data",articles);
            map.put("draw",draw);
            map.put("recordsTotal",count);
            map.put("recordsFiltered",count);
        }
        return map;
    }
    @RequestMapping("getArticleByUser")
    @ResponseBody
    public Map<String,Object> getArticleByUser(int draw,int start,int length,HttpSession session){
        Map<String,Object> map = new HashMap<>();
        User user = (User)session.getAttribute("user");
        String author = user.getUserName();
        List<Article> articles = articleService.getArticleByAuthor(start,length,author);
        int count = articleService.getAuthorArticleCount(author);
        if(count == 0){
            map.put("msg","用户暂无文章");
        }else {
            map.put("msg","搜索成功");
        }
        map.put("data",articles);
        map.put("draw",draw);
        map.put("recordsTotal",count);
        map.put("recordsFiltered",count);
        return map;
    }
    @RequestMapping("adminNewsSearch")
    public String adminNewsSearch(){
        return "adminNewsSearch";
    }
    @RequestMapping("addComment")
    @ResponseBody
    public Map<String,String> addComment(int userId,int articleId,String comment){
        Map<String,String> map = new HashMap<>();
        boolean flag = articleService.addComment(userId,articleId,comment);
        if (flag == true){
            map.put("msg","添加评论成功");
        }else{
            map.put("msg","添加评论失败");
        }
        return map;
    }
}
