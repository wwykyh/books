package com.dragon.book.service.impl;

import com.dragon.book.mapper.*;
import com.dragon.book.model.TBook;
import com.dragon.book.model.TStore;
import com.dragon.book.model.TSysUser;
import com.dragon.book.pojo.BookInfo;
import com.dragon.book.pojo.CommentInfo;
import com.dragon.book.pojo.HistoryInfo;
import com.dragon.book.pojo.QueryVo;
import com.dragon.book.service.BookManagerService;
import com.dragon.book.service.BookService;
import com.dragon.book.util.PageBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassNameBookManagerServiceImpl
 * @Description TODO
 * @Author liulei
 * @Date 2019/2/28
 */
@Service
public class BookManagerServiceImpl implements BookManagerService {

    @Autowired
    private TBookMapper tBookMapper;
    @Autowired
    private TStoreMapper storeMapper;
    @Autowired
    private BookManagerMapper mapperBook;
    @Autowired
    private TCommentMapper commentMapper;

    @Autowired
    private BookService bookService;
    @Autowired
    private UserMapper userMapper;


    @Override
    public TBook getBookById(String bookId) {
        return tBookMapper.selectByPrimaryKey(bookId);
    }

    @Override
    public boolean insertBook(QueryVo vo, MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException {

        TBook book = vo.getBook();
        TBook tBook = tBookMapper.selectByPrimaryKey(book.getIsbn());
        if (tBook == null) {
            book.setPicture(upPicture(file, request));
            System.out.println(book.toString());
            tBookMapper.insert(book);
        }
        TStore store = vo.getStore();
        //补全库存信息的属性
        store.setIsbn(book.getIsbn());
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(date);
        store.setRksj(time);
        if ("公司".equals(vo.getUserName())) {
            vo.setUserName("admin");
        }
        TSysUser tSysUser = userMapper.selectByName(vo.getUserName());
        store.setUserId(tSysUser.getUserId());
        // System.out.println(store.getWz()+"-------------");
        store.setId(bookService.getKey(store.getWz(), book.getTsdl()));
        // System.out.println(book.getTsdl()+"-------------");

        //是否在库 0 不在，1 在库
        store.setStatus(1);
        storeMapper.insert(store);
        return true;
    }

    @Override
    public boolean insertBook(QueryVo vo) {
        boolean flag=true;
        int i =tBookMapper.insert(vo.getBook());
        TStore store = vo.getStore();
        //补全库存信息的属性
        store.setIsbn(vo.getBook().getIsbn());
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(date);
        store.setRksj(time);
        if ("公司".equals(vo.getUserName())) {
            vo.setUserName("admin");
        }
        TSysUser tSysUser = userMapper.selectByName(vo.getUserName());
        store.setUserId(tSysUser.getUserId());
        // System.out.println(store.getWz()+"-------------");
        store.setId(bookService.getKey(store.getWz(), vo.getBook().getTsdl()));
        store.setStatus(1);
        storeMapper.insert(store);
        if (i==0)
            flag=false;
        return flag;
    }

    @Override
    public String upPicture(MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException {

        String filenewname = "";
        if (file.isEmpty()) {
            System.out.println("图片为空");

        } else {

            String fileName = file.getOriginalFilename();
            System.out.println("------file----------" + fileName);

            String path1 = request.getSession().getServletContext().getRealPath("/picture") + File.separator;

            filenewname = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + fileName;
            System.out.println("++++++++" + filenewname);
            String path = path1 + filenewname;

            System.out.println(path);

            File localFile = new File(path);
            file.transferTo(localFile);

            return "picture/" + filenewname;
        }
        return "";
    }

    @Override
    public PageBean selectBookInfo(PageBean pageBean, QueryVo vo) {
        if (vo.getDim() == null || vo.getDim() == "") {
            vo.setDim(null);
        }
        int total = mapperBook.selectByDimTotal(vo);
        pageBean.setTotal(total);
        List<BookInfo> tBooks = mapperBook.selectByDimPage(vo);
        System.out.println(tBooks.toString());
        pageBean.setRows(tBooks);
        return pageBean;
    }

    @Override
    public PageBean selectCommentInfo(PageBean pageBean, QueryVo vo) {
        int total = mapperBook.selectCommentByIsbnTotal(vo);
        pageBean.setTotal(total);
        List<CommentInfo> commentList = mapperBook.selectCommentByIsbnList(vo);
        pageBean.setRows(commentList);
        return pageBean;
    }

    @Override
    public BookInfo selectBookInfoById(String id) {
        return mapperBook.selectBookInfoById(id);
    }

    @Override
    public boolean delBook(String id) {
        int rows = storeMapper.deleteByPrimaryKey(id);
        return rows > 0 ? true : false;
    }

    @Override
    public boolean delComment(String id) {
        int i = commentMapper.deleteByPrimaryKey(Integer.parseInt(id));
        return i > 0 ? true : false;
    }

    @Override
    public CommentInfo selectCommentInfoById(String id) {
        CommentInfo commentInfo = mapperBook.selectCommentInfoById(Integer.parseInt(id));
        return commentInfo;
    }

    @Override
    public List<BookInfo> selectAllBookInfo() {
        return mapperBook.bookExport();
    }

    @Override
    public PageBean selectHistoryInfo(PageBean pageBean) {
        PageHelper.startPage(pageBean.getPage(), pageBean.getPagesize());
        List<HistoryInfo> list = mapperBook.selectHistoryPage(pageBean);
        PageInfo<HistoryInfo> info = new PageInfo<>(list);
        pageBean.setTotal((int) info.getTotal());
        pageBean.setRows(info.getList());
        return pageBean;
    }

    @Override
    public HistoryInfo selectHistoryById(Integer id) {
        return mapperBook.selectHistoryById(id);
    }

    @Override
    public BookInfo selectBookByIsbn(String Isbn) {
        BookInfo bookInfo = new BookInfo();
        List<BookInfo> bookInfos = mapperBook.selectBookInfoByIsbn(Isbn);
        for (int i = 0; i < bookInfos.size(); i++) {
            if (bookInfos.get(i).getStatus().equals("在库")) {
                bookInfo = bookInfos.get(i);
                break;
            }
        }
        if (null == bookInfo.getId() && bookInfos != null && bookInfos.size() != 0)
            bookInfo = bookInfos.get(0);
        return bookInfo;
    }

    public TBookMapper gettBookMapper() {
        return tBookMapper;
    }

    public void settBookMapper(TBookMapper tBookMapper) {
        this.tBookMapper = tBookMapper;
    }

    public TStoreMapper getStoreMapper() {
        return storeMapper;
    }

    public void setStoreMapper(TStoreMapper storeMapper) {
        this.storeMapper = storeMapper;
    }

    public BookManagerMapper getMapperBook() {
        return mapperBook;
    }

    public void setMapperBook(BookManagerMapper mapperBook) {
        this.mapperBook = mapperBook;
    }

    public TCommentMapper getCommentMapper() {
        return commentMapper;
    }

    public void setCommentMapper(TCommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public BookService getBookService() {
        return bookService;
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
