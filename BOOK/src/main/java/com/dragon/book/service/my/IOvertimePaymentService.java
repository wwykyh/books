package com.dragon.book.service.my;

import com.dragon.book.model.TBorrow;

import java.util.List;

public interface IOvertimePaymentService {

    /**
     *查询超时图书
     * @param uid 用户ID
     * @return
     */
    public List<TBorrow> findOvertimeBorrow(int uid);
}
