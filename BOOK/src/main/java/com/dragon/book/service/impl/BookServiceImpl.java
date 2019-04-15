package com.dragon.book.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import com.dragon.book.mapper.TStoreMapper;
import com.dragon.book.model.*;
import com.dragon.book.pojo.Book;
import com.dragon.book.util.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dragon.book.mapper.BookMapper;
import com.dragon.book.mapper.BorrowMapper;
import com.dragon.book.mapper.TBorrowMapper;
import com.dragon.book.service.BookService;
import com.dragon.book.util.PageBean;

@Service
public class BookServiceImpl implements BookService {

    final int Max = 100;

    @Autowired
    BorrowMapper borrowMapper;

    @Autowired
    TBorrowMapper tBorrowMapper;

    @Autowired
    BookMapper bookMapper;

    @Autowired
    TStoreMapper storeMapper;


    @Override
    public List<BookAndEBook> getBooks(Page pagebean) {
        return null;
    }

    @Override
    public List<TBorrow> getBookTop() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<TBorrow> getUserTop() {
        // TODO Auto-generated method stub
        return null;
    }


    public List<Book> getBooks(PageBean pagebean) {
        // TODO Auto-generated method stub
        return bookMapper.selectByDim(pagebean);
    }

    @Override
    public List<BookAndEBook> getEBooks(PageBean pagebean) {
        // TODO Auto-generated method stub
        return bookMapper.selectEBookByDim(pagebean);
    }


    public int getTotal(PageBean pagebean) {
        // TODO Auto-generated method stub
        return bookMapper.getTotal(pagebean);
    }

    @Override
    public Book getBook(String id) {
        // TODO Auto-generated method stub
        return bookMapper.selectById(id);
    }

    @Override
    public List<BookAndEBook> getBooksKey() {
        // TODO Auto-generated method stub
        return bookMapper.getBooksKey();
    }

    @Override
    public List<BookAndEBook> getEBookKey() {
        // TODO Auto-generated method stub
        return bookMapper.getEBookKey();
    }

    @Override
    public int insertBorrow(TBorrow borrow) {
        // TODO Auto-generated method stub
        return tBorrowMapper.insertSelective(borrow);
    }

    @Override
    public String NowTime() {
        long time = System.currentTimeMillis();
        Date date = new Date(time);
        String ma = "yyyyMMdd";

        SimpleDateFormat forma = new SimpleDateFormat(ma);
        String nwdate = forma.format(date);
        return nwdate;
    }

    @Override
    public String getKey(String wz, String tsdl) {
        // TODO Auto-generated method stub
        String id = "DBD";
        String st = null;

        if ("纸质书".equals(tsdl) || "纸质".equals(tsdl)) {
            id = id + "Z";
            st = getDbKey("Z");
        } else {
            id = id + "E";
            st = getDbKey("E");
        }


        id = id + wz + NowTime() + st;

        return id;
    }

    @Override
    public String getDbKey(String tsdl) {
        // TODO Auto-generated method stub

        try {
            List<BookAndEBook> allKey = new ArrayList<BookAndEBook>();
            if ("Z".equals(tsdl)) {
                allKey = getBooksKey();
            } else if ("E".equals(tsdl)) {
                allKey = getEBookKey();
            }

           /* allKey.addAll(bookKey);
            allKey.addAll(ebookKey);*/
            // HashSet<String> set = new HashSet<String>();
            List<String> set = new ArrayList();
            for (BookAndEBook b : allKey) {

                String str = b.getId().substring(b.getId().length() - 11);
                //  System.out.println("key"+str);
                if (str.contains(NowTime())) {
                    String s = str.substring(str.length() - 3);
                    //  System.out.println("key"+s);
                    set.add(s);
                }
            }

            /*for (int i =0;i<set.size();i++){
                System.out.println(set.get(i)+"+set");
            }*/

            Random rd = new Random();
            Boolean flag = true;
            do {
                String st;
                int num = rd.nextInt(Max);
                if (num < 10)
                    st = "00" + num;
                else
                    st = "0" + num;
                //   String st = "" + t;
                if (!set.contains(st)) {
                  /*  System.out.println("st+"+st);
                    if (st.length() == 1) {
                        st = "00" + st;
                    }
                    if (st.length() == 2) {
                        st = "0" + st;
                    }*/

                    // System.out.println(flag+"================flag");

                    flag = false;
                    return st;
                }
            } while (flag);
        } catch (NullPointerException e) {
            System.out.println(e);
        }

        return null;
    }

    @Override
    public String getTime(String jyrq, String jhghrq) {
        // TODO Auto-generated method stub
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = sdf.parse(jyrq);// 字符串转换为data
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, Integer.parseInt(jhghrq));
        String time = sdf.format(cal.getTime());// data转换为字符串
        return time;
    }

    @Override
    public List<BookAndEBook> joinBook(List<BookAndEBook> booksList,
                                       List<BookAndEBook> EBookslist) {
        // TODO Auto-generated method stub

        List<BookAndEBook> books = new ArrayList<>();
        books.addAll(booksList);
        books.addAll(EBookslist);
        return books;
    }

    public TBorrow setBorrow(String isbn, int userId, String sm, String lxfs, String jyrq, String jhghrq, int i, int jyzt) {

        TBorrow borrow = new TBorrow();
        borrow.setsId(isbn);
        borrow.setUserId(userId);
        borrow.setSm(sm);
        borrow.setLxfs(lxfs);
        borrow.setJyrq(jyrq);
        borrow.setJhghrq(jhghrq);
        borrow.setStatus(i);
        borrow.setJyzt(jyzt);
        return borrow;
    }

    public int updateByKey(String id, int status) {
        TStore store = new TStore();
        store.setId(id);
        store.setStatus(status);
        int i = storeMapper.updateByPrimaryKeySelective(store);
        return i;
    }

    @Override
    public Page<Book> getPage(int pageNumber, int pageSize, String dim, String s_type, int total, String status) {
        //	int pageNo=1;      //设置默认页码，当pageNumber类型转换出错时，会起作用，否则值被覆盖
        Page<Book> page = null;
        PageBean pageBean = new PageBean();
        pageBean.setDim(dim);
        pageBean.setS_type(s_type);
        pageBean.setPagesize(pageSize);
        pageBean.setStatus(status);

		/*try {
			//servlet层获取的参数类型为string，需要转换为整型
			pageNo=Integer.parseInt(pageNumber);
		} catch (Exception e) {
			System.out.println("字符串转换出错");
		}*/
        //1.获取总记录数
        int totalRecord = bookMapper.getTotal(pageBean);
        //2.封装page对象
        System.out.println("+++++" + pageNumber + ":" + dim);

        page = new Page<Book>(pageNumber, total, pageSize);
        pageBean.setPage(page.getIndex());
        System.out.println("page:" + pageBean.getPage() + "pagesize:" + pageBean.getPagesize() + pageBean.toString());
        //3.查询当前页对应的数据列表并封装到page对象中
        List<Book> list = bookMapper.selectByDim(pageBean);
        System.out.println("list："+list.toString());
        page.setList(list);
        page.setDim(dim);
        page.setS_type(s_type);
        page.setStatus(status);
//        System.out.println(list.toString() + "size:" + list.size() + list.get(0).gettStore().getId());
        return page;
    }

    @Override
    public List<Book> getPageInfo(String dim, String s_type, int pageNum) {
        PageHelper.startPage(pageNum, 2);
        PageBean pageBean = new PageBean();
        List<Book> list = bookMapper.selectByDim(pageBean);
        return list;
    }

    @Override
    public int getBorrow(int id) {
        int borrow = borrowMapper.getBorrow(id, 2);
        //  System.out.println(borrow.toString());

        return borrow;
    }

    public BorrowMapper getBorrowMapper() {
        return borrowMapper;
    }

    public void setBorrowMapper(BorrowMapper borrowMapper) {
        this.borrowMapper = borrowMapper;
    }

    public BookMapper getBookMapper() {
        return bookMapper;
    }

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public int getMax() {
        return Max;
    }

    public TBorrowMapper gettBorrowMapper() {
        return tBorrowMapper;
    }

    public void settBorrowMapper(TBorrowMapper tBorrowMapper) {
        this.tBorrowMapper = tBorrowMapper;
    }

    public TStoreMapper getStoreMapper() {
        return storeMapper;
    }

    public void setStoreMapper(TStoreMapper storeMapper) {
        this.storeMapper = storeMapper;
    }
}
