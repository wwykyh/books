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
     * @param userid
     * @return
     */
    public List<BookBorrow> selectBookInformation(@Param("userid")int userid);

    /**
     * 查询用户信息
     * @param userid
     * @return
     */
    public TSysUser selectUserInformation(@Param("userid")int userid);

    /**
     * 归还图书
     * @param isbn
     * @param userid
     */
    public void updateBorrow(@Param("isbn")String isbn,@Param("userid")int userid);

    /**
     * 续借图书
     * @param isbn
     * @param userid
     */
    public void updatejszt(@Param("isbn")String isbn,@Param("userid")int userid);

    /**
     * 借阅详情
     * @param id
     * @return
     */
    public TBorrow selectBorrowInfo(@Param("id")int id);

}
