package com.dragon.book.service;

import com.dragon.book.model.TBorrow;
import com.dragon.book.model.TSystemConfig;
import com.dragon.book.pojo.Borrow;

import java.util.List;


public interface HomeService {
    /**
     * 获取用户所借阅图书信息
     *
     * @return
     */
    public List<TBorrow> getUserBorrow(int userid);

    /**
     * 获取超时用户信息
     *
     * @return
     */
    public String getOverTimeUser();

    /**
     * 获取排行榜
     *
     * @return
     */
    public List<Borrow> getRankingList();

    /**
     * 获取热门图书
     *
     * @return
     */
    public List<Borrow> getHotBooks();

    /**
     * 获取新上架图书
     *
     * @return
     */
    public List<Borrow> getNewBooks();

    /**
     * 传入图书sid还书申请
     *
     * @return
     */
    public boolean returnBookRequest(String[] BookSid);
    /**
     * 获取用户总数
     *
     * @return
     */
    public int userTotal();
    /**
     * 获取图书总数
     *
     * @return
     */
    public int bookTotal();
    /**
     * 获取配置信息
     *
     * @return
     */
    public TSystemConfig getConfig();/**
     * 修改黑名单惩罚时间
     *
     * @return
     */
    public int modifyPenTime(int penTime);
}
