package com.dragon.book.service.my.impl;

import com.dragon.book.mapper.my.NewsDao;
import com.dragon.book.model.TBookNews;
import com.dragon.book.model.TBorrow;
import com.dragon.book.service.my.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 我的：消息Service
 */
@Service
public class NewsServiceImpl implements INewsService {

    @Autowired
    private NewsDao newsDao;

    /**
     * 获得个人消息总览
     * @param userId
     * @return
     */
    public List<TBookNews> findNews(int userId){
        List<TBookNews> tBookNews = newsDao.findNews(userId);
        return tBookNews;
    }

    /**
     * 删除信息
     * @param id
     */
    public void deleteNews(int id){
        newsDao.deleteNews(id);
    }

    public TBorrow findDetailInfo(String isbn,int uId){
        return newsDao.findDetailInfo(isbn, uId);
    }
}
