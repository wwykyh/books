package com.dragon.book.mapper.my;

import com.dragon.book.model.TBorrow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 超时归还
 * zzm
 */
public interface OvertimePaymentDao {

    /**
     * 查询超时图书
     * @param uid 用户id
     * @return
     */
    public List<TBorrow> findOvertimeBorrow(@Param("userId") int uid,@Param("currentTime") String currentTime);

}
