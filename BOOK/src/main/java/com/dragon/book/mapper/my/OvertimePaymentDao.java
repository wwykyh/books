package com.dragon.book.mapper.my;

import com.dragon.book.model.TBorrow;
import com.dragon.book.model.TSysUser;
import com.dragon.book.pojo.UserBorrow;
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
    public List<TBorrow> findOvertimeBorrow(@Param("userId") int uid);

    /**
     * 差寻用户是否有超时图书
     * @return
     */
    public List<UserBorrow> findOvertimeUser();

}
