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
     * 差寻用户是否有超时图书,有超时则发送提醒邮件
     * @return
     */
    public void findOvertimeUser();
}
