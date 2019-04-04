package com.dragon.book.mapper.my;

import com.dragon.book.model.TBorrow;
import com.dragon.book.model.TSysUser;
import com.dragon.book.pojo.BookBorrow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 我的:个人信息Dao
 * zzm
 */
public interface PersonalDao {

    /**
     * 查询借书信息
     * @param userId
     * @return
     */
    public List<BookBorrow> selectBookInformation(@Param("userId")int userId);

    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    public TSysUser selectUserInformation(@Param("userId")int userId);

    /**
     * 归还图书
     * @param isbn
     * @param userId
     */
    public void updateBorrow(@Param("isbn")String isbn,@Param("userId")int userId);

    /**
     * 续借图书
     * @param isbn
     * @param userId
     */
    public void updatejszt(@Param("isbn")String isbn,@Param("userId")int userId);

    /**
     * 借阅详情
     * @param id
     * @return
     */
    public TBorrow selectBorrowInfo(@Param("id")int id);

}
