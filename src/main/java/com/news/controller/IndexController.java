package com.news.controller;

import com.news.model.Article;
import com.news.model.Hits;
import com.news.model.SystemInfo;
import com.news.model.User;
import com.news.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by zhongziming on 2017/5/3.
 */
@Controller
public class IndexController {
    private ArticleService articleService = null;
    private UserService userService = null;
    private AdminService adminService = null;
    private HitsService hitsService = null;

    public HitsService getHitsService() {
        return hitsService;
    }
    @Autowired
    public void setHitsService(HitsService hitsService) {
        this.hitsService = hitsService;
    }

    public AdminService getAdminService() {
        return adminService;
    }
    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    public UserService getUserService() {
        return userService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setArticleService(ArticleService articleService){
        this.articleService = articleService;
    }
    public ArticleService getArticleService(){
        return this.articleService;
    }
    @RequestMapping("article")
    public ModelAndView getArticleById(int id){
        ModelAndView model = new ModelAndView();
        Article article = articleService.getArticleByIdToRead(id);
        List<Hits> hits = hitsService.getHits();
        if(article == null){
            model.addObject("msg","获取新闻失败");
        }else {
            model.addObject("msg","获取新闻成功");
            model.addObject("article",article);
            model.addObject("hits",hits);
            model.setViewName("article");
        }
        return model;
    }
    @RequestMapping("{path}")
    public String path(@PathVariable("path") String path){
        return path;
    }
    @RequestMapping("login")
    public String path(HttpSession session){
        session.setAttribute("user",null);
        session.setAttribute("admin",null);
        return "login";
    }
    @RequestMapping("loginToArticle")
    public String loginToArticle(User user, int articleId, HttpSession session){
        boolean flag = userService.checkUser(user.getEmail(),user.getPassword());
        if (flag == true){
            user = userService.getUser(user.getEmail());
            session.setAttribute("user",user);
            return "redirect:/article?id="+articleId;
        }
        return "redirect:/login";
    }
}
