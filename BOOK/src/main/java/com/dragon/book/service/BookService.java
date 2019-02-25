package com.dragon.book.service;

import com.dragon.book.model.TBook;
import com.dragon.book.pojo.BookInfo;
import com.dragon.book.pojo.CommentInfo;
import com.dragon.book.pojo.PageBean;
import com.dragon.book.pojo.QueryVo;

public interface BookService {
    TBook getBookById(String bookId) ;
    boolean insertBook(QueryVo vo) ;
    PageBean selectBookInfo(PageBean pageBean,QueryVo vo) ;
    BookInfo selectBookInfoById(String id) ;
    CommentInfo selectCommentInfoById(String id) ;
    boolean delBook(String id) ;
    boolean delComment(String id) ;
    PageBean selectCommentInfo(PageBean pageBean,QueryVo vo) ;
}
