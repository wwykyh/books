package com.dragon.book.service.my;

import com.dragon.book.model.TBorrow;
import com.dragon.book.pojo.UserBorrow;

import java.util.List;

public interface IOvertimePaymentService {

    /**
     *查询超时图书
     * @param uid 用户ID
     * @return
     */
    public List<TBorrow> findOvertimeBorrow(int uid);

    /**
     * 添加超时信息
     */
    public void addOvertimeNews();


}
