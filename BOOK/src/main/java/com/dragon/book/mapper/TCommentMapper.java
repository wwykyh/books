package com.dragon.book.mapper;

import com.dragon.book.model.TComment;
import com.dragon.book.model.TCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TCommentMapper {
    int countByExample(TCommentExample example);

    int deleteByExample(TCommentExample example);

    int deleteByPrimaryKey(Integer commentId);

    int insert(TComment record);

    int insertSelective(TComment record);

    List<TComment> selectByExampleWithBLOBs(TCommentExample example);

    List<TComment> selectByExample(TCommentExample example);

    TComment selectByPrimaryKey(Integer commentId);

    int updateByExampleSelective(@Param("record") TComment record, @Param("example") TCommentExample example);

    int updateByExampleWithBLOBs(@Param("record") TComment record, @Param("example") TCommentExample example);

    int updateByExample(@Param("record") TComment record, @Param("example") TCommentExample example);

    int updateByPrimaryKeySelective(TComment record);

    int updateByPrimaryKeyWithBLOBs(TComment record);

    int updateByPrimaryKey(TComment record);

    List< TComment> selectByUserId(String UserId);
}