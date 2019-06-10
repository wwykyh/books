package com.dragon.book.service;

import com.dragon.book.model.TBook;
import com.dragon.book.pojo.BookInfo;
import com.dragon.book.pojo.CommentInfo;
import com.dragon.book.pojo.HistoryInfo;
import com.dragon.book.pojo.QueryVo;
import com.dragon.book.util.PageBean;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface BookManagerService {
    TBook getBookById(String bookId) ;
    boolean insertBook(QueryVo vo, MultipartFile file,HttpServletRequest request) throws IllegalStateException, IOException;
    boolean insertBook(QueryVo vo);
    PageBean selectBookInfo(PageBean pageBean, QueryVo vo) ;
    BookInfo selectBookInfoById(String id) ;
    CommentInfo selectCommentInfoById(String id) ;
    boolean delBook(String id) ;
    boolean delComment(String id) ;
    PageBean selectCommentInfo(PageBean pageBean,QueryVo vo) ;
    List<BookInfo> selectAllBookInfo() ;
    PageBean selectHistoryInfo(PageBean pageBean) ;
    HistoryInfo selectHistoryById(Integer id) ;
    String upPicture(MultipartFile file, HttpServletRequest request)throws IllegalStateException, IOException;
    //通过Isbn查一本在库的图书信息，如果没有在库的图书则返回一个不在库的图书信息
    BookInfo selectBookByIsbn(String Isbn);
}
