package com.dragon.book.service.my.impl;

import com.dragon.book.mapper.my.NewsDao;
import com.dragon.book.model.TBookNews;
import com.dragon.book.model.TBorrow;
import com.dragon.book.pojo.BookNews;
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
     *
     * @param userId
     * @return
     */
    public List<BookNews> findNews(int userId) {
        List<BookNews> bookNews = newsDao.findNews(userId);
        newsDao.updateState(userId);
        return bookNews;
    }

    /**
     * 删除信息
     *
     * @param id
     */
    public void deleteNews(int id) {
        newsDao.deleteNews(id);
    }

    /**
     * 查找详细信息
     *
     * @param isbn
     * @param uId
     * @return
     */
    public TBorrow findDetailInfo(String isbn, int uId) {
        return newsDao.findDetailInfo(isbn, uId);
    }

    /**
     * 删除多个消息
     *
     * @param check
     */
    public String deleteMultipleNews(Integer[] check) {
        String message;
        if (null != check) {
            for (int i = 0; i < check.length; i++) {
                if (0 != check[i] && null != check[i]) {
                    newsDao.deleteNews(check[i]);
                }
            }
            return "删除成功";
        }
        return "删除对象不能为空";
    }


    public List<TBookNews> findNewsState(){
        return newsDao.findNewsState();
    }
}
