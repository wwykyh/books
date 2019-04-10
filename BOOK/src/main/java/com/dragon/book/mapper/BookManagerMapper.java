package com.dragon.book.mapper;

import com.dragon.book.pojo.*;
import com.dragon.book.util.PageBean;

import java.util.List;

/**
 * @ClassNameBookMapper
 * @Description TODO
 * @Author liulei
 * @Date 2019/1/29
 */
public interface BookManagerMapper {
    int selectByDimTotal(QueryVo vo) ;
    List<BookInfo> selectByDimPage(QueryVo vo) ;
    int selectCommentByIsbnTotal(QueryVo vo) ;
    List<CommentInfo> selectCommentByIsbnList(QueryVo vo) ;
    BookInfo selectBookInfoById(String id) ;
    CommentInfo selectCommentInfoById(Integer commentId) ;
    List<BookInfo> bookExport() ;
    List<HistoryInfo> selectHistoryPage(PageBean pageBean) ;
    HistoryInfo selectHistoryById(Integer id) ;
    List<BookInfo> selectBookInfoByIsbn(String Isbn);
}
