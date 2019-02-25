package com.dragon.book.mapper;

import com.dragon.book.model.TBook;
import com.dragon.book.pojo.BookInfo;
import com.dragon.book.pojo.CommentInfo;
import com.dragon.book.pojo.QueryVo;

import java.util.List;

/**
 * @ClassNameBookMapper
 * @Description TODO
 * @Author liulei
 * @Date 2019/1/29
 */
public interface BookInfoMapper {
    int selectByDimTotal(QueryVo vo) ;
    List<BookInfo> selectByDimPage(QueryVo vo) ;
    int selectCommentByIsbnTotal(QueryVo vo) ;
    List<CommentInfo> selectCommentByIsbnList(QueryVo vo) ;
    BookInfo selectBookInfoById(Integer id) ;
    CommentInfo selectCommentInfoById(Integer commentId) ;
}
