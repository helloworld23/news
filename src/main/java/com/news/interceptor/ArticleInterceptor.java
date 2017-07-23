package com.news.interceptor;

import com.news.exception.NoArticleFoundException;
import com.news.model.Article;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by zhongziming on 2017/5/22.
 */
public class ArticleInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        Map<String,Object> model = (Map<String, Object>) modelAndView.getModel();
        Article article = (Article) model.get("article");
        if (article == null){
            System.out.println("article null");
            modelAndView.setViewName("NoArticleFound");
        }else{
            System.out.println("article not null");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
