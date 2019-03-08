package com.dragon.book.service.impl;

import com.dragon.book.mapper.BookManagerMapper;
import com.dragon.book.mapper.TBookMapper;
import com.dragon.book.mapper.TCommentMapper;
import com.dragon.book.mapper.TStoreMapper;
import com.dragon.book.model.TBook;
import com.dragon.book.model.TStore;
import com.dragon.book.pojo.BookInfo;
import com.dragon.book.pojo.CommentInfo;
import com.dragon.book.pojo.HistoryInfo;
import com.dragon.book.pojo.QueryVo;
import com.dragon.book.service.BookManagerService;
import com.dragon.book.util.PageBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private TBookMapper tBookMapper ;
    @Autowired
    private TStoreMapper storeMapper ;
    @Autowired
    private BookManagerMapper mapperBook ;
    @Autowired
    private TCommentMapper commentMapper ;


    @Override
    public TBook getBookById(String bookId) {
        return tBookMapper.selectByPrimaryKey(bookId);
    }

    @Override
    public boolean insertBook(QueryVo vo) {

        TBook book = vo.getBook();
        TBook tBook = tBookMapper.selectByPrimaryKey(book.getIsbn());
        if (tBook == null){
            tBookMapper.insert(book) ;
        }
        TStore store = vo.getStore();
        //补全库存信息的属性
        store.setIsbn(book.getIsbn());
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd") ;
        String time = format.format(date);
        store.setRksj(time);
        //是否在库 0 不在，1 在库
        store.setStatus(1);
        storeMapper.insert(store) ;
        return true;
    }

    @Override
    public PageBean selectBookInfo(PageBean pageBean, QueryVo vo) {
        if (vo.getDim() == null || vo.getDim() ==""){
            vo.setDim(null);
        }
        int total = mapperBook.selectByDimTotal(vo);
        pageBean.setTotal(total);
        List<BookInfo> tBooks = mapperBook.selectByDimPage(vo);
        pageBean.setRows(tBooks);
        return pageBean;
    }

    @Override
    public PageBean selectCommentInfo(PageBean pageBean, QueryVo vo) {
        int total = mapperBook.selectCommentByIsbnTotal(vo) ;
        pageBean.setTotal(total);
        List<CommentInfo> commentList = mapperBook.selectCommentByIsbnList(vo);
        pageBean.setRows(commentList);
        return pageBean;
    }

    @Override
    public BookInfo selectBookInfoById(String id) {
        return mapperBook.selectBookInfoById(Integer.parseInt(id));
    }

    @Override
    public boolean delBook(String id) {
        int rows = storeMapper.deleteByPrimaryKey(id) ;
        return rows > 0 ? true : false;
    }

    @Override
    public boolean delComment(String id) {
        int i = commentMapper.deleteByPrimaryKey(Integer.parseInt(id));
        return i > 0 ? true : false ;
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
        PageHelper.startPage(pageBean.getPage(),pageBean.getPagesize()) ;
        List<HistoryInfo> list = mapperBook.selectHistoryPage(pageBean);
        PageInfo<HistoryInfo> info = new PageInfo<>(list) ;
        pageBean.setTotal((int)info.getTotal());
        pageBean.setRows(info.getList());
        return pageBean;
    }

    @Override
    public HistoryInfo selectHistoryById(Integer id) {
        return mapperBook.selectHistoryById(id);
    }
}
