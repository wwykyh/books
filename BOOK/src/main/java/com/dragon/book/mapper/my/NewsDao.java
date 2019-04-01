package com.dragon.book.mapper.my;

import com.dragon.book.model.TBookNews;
import com.dragon.book.model.TBorrow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsDao {

    /**
     * 获得个人消息总览
     * @param userId
     * @return
     */
    public List<TBookNews> findNews(@Param("userId") int userId);

    /**
     * 删除用户信息
     * @param id
     */
    public void deleteNews(@Param("id") int id);

    /**
     * 查询消息详情
     * @param isbn
     * @param userId
     */
    public TBorrow findDetailInfo(@Param("isbn") String isbn, @Param("userId") int userId);
}
