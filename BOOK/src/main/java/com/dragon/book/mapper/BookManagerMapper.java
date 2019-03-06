package com.dragon.book.mapper;

import com.dragon.book.pojo.BookInfo;
import com.dragon.book.pojo.CommentInfo;
import com.dragon.book.pojo.HistoryInfo;
import com.dragon.book.pojo.QueryVo;
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
    BookInfo selectBookInfoById(Integer id) ;
    CommentInfo selectCommentInfoById(Integer commentId) ;
    List<BookInfo> bookExport() ;
    List<HistoryInfo> selectHistoryPage(PageBean pageBean) ;
}
