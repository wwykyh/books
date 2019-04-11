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
     * 查询超时用户信息
     * @return
     */
    public List<UserBorrow> findOvertimeUser();

    /**
     * 查找所有借阅信息
     * @return
     */
    public List<TBorrow> findAllOvertimeBorrow();

    /**
     * 添加新的超时消息
     */
    public void addOvertimeNews(@Param("userId") int uId,@Param("isbn") String s_id,@Param("date") String date);
}
