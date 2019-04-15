package com.dragon.book.service.my;

import com.dragon.book.model.TBorrow;
import com.dragon.book.pojo.BookNews;

import java.util.List;

public interface INewsService {

    /**
     * 获得个人消息总览
     *
     * @param userId
     * @return
     */
    public List<BookNews> findNews(int userId);

    /**
     * 删除信息
     *
     * @param id
     */
    public void deleteNews(int id);

    /**
     * 查找详细信息
     *
     * @param isbn
     * @param uId
     * @return
     */
    public TBorrow findDetailInfo(String isbn, int uId);

    /**
     * 删除多个消息
     *
     * @param check
     */
    public String deleteMultipleNews(Integer[] check);

}
