package com.dragon.book.controller;

import com.dragon.book.model.TBookNews;
import com.dragon.book.model.TBorrow;
import com.dragon.book.service.my.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 我的：消息模块
 * zzm
 */
@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsServiceImpl newsServiceImpl;

    /**
     * 获得个人消息总览
     * @param uid
     * @param map
     * @return
     */
    @RequestMapping("/tonews")
    public String toNewsIndex(@RequestParam("userid")String uid, Map map){
        int userid = Integer.parseInt(uid);
        if(0!=userid){
            List<TBookNews> tBookNews = newsServiceImpl.getNews(userid);
            map.put("tBookNews",tBookNews);
        }
        return "/my/news";
    }

    /**
     * 删除消息
     * @param id
     * @return
     */
    @RequestMapping("/deleteNews")
    @ResponseBody
    public String deleteNews(@RequestParam("id")int id){
        if(0!=id){
            newsServiceImpl.deleteNews(id);
            return "success";
        }
        return "error";
    }

    /**
     * 获取详细信息
     * @param isbn
     * @param uid
     * @param map
     * @return
     */
    @RequestMapping("/toNewsDetailInfo")
    public String toNewsDetailInfo(@RequestParam("isbn")int isbn,@RequestParam("userid")int uid, Map map){
        TBorrow tBorrow = newsServiceImpl.findDetailInfo(isbn,uid);
        map.put("tborrow",tBorrow);
        return "/my/bookNewsInfo";
    }
}
