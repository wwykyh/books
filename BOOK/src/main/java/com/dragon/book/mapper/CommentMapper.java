package com.dragon.book.mapper;

import com.dragon.book.model.TComment;
import com.dragon.book.model.TCommentExample;
import com.dragon.book.pojo.CommentInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper extends TCommentMapper{

    List<CommentInfo> selectByBorrowId(Integer borrowId);
    List<CommentInfo> selectByBorrow(Integer borrowId);
   List<CommentInfo> selectComment(String id);
}