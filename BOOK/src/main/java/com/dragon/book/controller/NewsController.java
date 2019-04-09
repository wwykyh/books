package com.dragon.book.controller;

import com.dragon.book.model.TBookNews;
import com.dragon.book.model.TBorrow;
import com.dragon.book.service.my.INewsService;
import com.dragon.book.service.my.impl.NewsServiceImpl;
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
    private INewsService newsService;

    /**
     * 获得个人消息总览
     * @param uId 用户ID
     * @param map
     * @return
     */
    @RequestMapping("/toNews")
    public String toNewsIndex(@RequestParam("userId")int uId, Map map){
        if(0!=uId){
            List<TBookNews> tBookNews = newsService.findNews(uId);
            map.put("tBookNews",tBookNews);
            return "/my/news";
        }else {
            return "/my/error";
        }
    }

    /**
     * 删除消息
     * @param id 消息ID
     * @return
     */
    @RequestMapping("/deleteNews")
    @ResponseBody
    public String deleteNews(@RequestParam("id")int id){
        String message;
        if(0!=id){
            newsService.deleteNews(id);
            return "success";
        }
        message = "删除失败！";
        return message;
    }

    /**
     * 获取详细信息
     * @param isbn 图书编号
     * @param uId 用户ID
     * @param map
     * @return
     */
    @RequestMapping("/toNewsDetailInfo")
    public String toNewsDetailInfo(@RequestParam("isbn")String isbn, @RequestParam("userId")int uId, Map map){
        if(null!=isbn&&""!=isbn&&0!= uId) {
            TBorrow tBorrow = newsService.findDetailInfo(isbn, uId);
            map.put("tborrow", tBorrow);
            return "/my/bookNewsInfo";
        }else {
            return "/my/error";
        }
    }
}
