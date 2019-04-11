package com.dragon.book.service.impl;

import com.dragon.book.mapper.BorrowMapper;
import com.dragon.book.mapper.TSystemConfigMapper;
import com.dragon.book.mapper.UserMapper;
import com.dragon.book.mapper.my.OvertimePaymentDao;
import com.dragon.book.model.TBorrow;
import com.dragon.book.model.TSystemConfig;
import com.dragon.book.pojo.Borrow;
import com.dragon.book.pojo.UserBorrow;
import com.dragon.book.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
     private UserMapper userMapper;

    @Autowired
    private BorrowMapper borrowMapper;

    @Autowired
    private OvertimePaymentDao overtimePaymentDao;

    @Autowired
    private TSystemConfigMapper tSystemConfigMapper;

    @Override
    public List<TBorrow> getUserBorrow(int userid) {
        List<TBorrow> books = userMapper.selectUserBorrow(userid);
        return books;
    }

    @Override
    public String getOverTimeUser() {
        String a = "<font color='red'><b>";
        List<UserBorrow> users  = overtimePaymentDao.findOvertimeUser();
        for (int i=0;i<users.size();i++){
            a = a+users.get(i).gettSysUser().getBm()+"</font><font color='yellow'>"+users.get(i).gettSysUser().getXm()+
                    "</font><font color='red'>借的"+ users.get(i).getSm()+"逾期尚未归还，请尽快归还！！！&emsp;&emsp;&emsp;&" +
                    "emsp;&emsp;&emsp;&emsp;&emsp;&emsp;";
        }
        a=a+"</b></font>";
        return a;
    }

    @Override
    public List<Borrow> getRankingList() {
        List<Borrow> users = borrowMapper.selectByUserTop();
        return users;
    }

    //传递的数据保证6个，防止主页样式崩溃
    @Override
    public List<Borrow> getHotBooks() {
        List<Borrow> hotBooks = borrowMapper.selectByBookTop();
        if (hotBooks.size()<6){
            do {
                Borrow borrow = new Borrow();
                hotBooks.add(borrow);
            }while (hotBooks.size()<6);
        }
        return hotBooks;
    }


    //传递的数据保证6个，防止主页样式崩溃
    @Override
    public List<Borrow> getNewBooks() {
        List<Borrow> newBooks = borrowMapper.selectByBookNew();
        if (newBooks.size()<6){
            do {
                Borrow borrow = new Borrow();
                newBooks.add(borrow);
            }while (newBooks.size()<6);
        }
        return newBooks;
    }

    @Override
    public boolean returnBookRequest(String[] BookSid) {
        int status = 0;
        for (int i=0;i<BookSid.length;i++){
            int a = borrowMapper.returnBookRequest(BookSid[i]);
            status=status+a;
        }
        return status>0?true:false;
    }

    @Override
    public int userTotal() {
        return userMapper.userTotal();
    }

    @Override
    public int bookTotal() {
        return userMapper.bookTotal();
    }

    @Override
    public TSystemConfig getConfig() {
        return tSystemConfigMapper.selectOneConfig();
    }

    @Override
    public int modifyPenTime(int penTime) {
        return tSystemConfigMapper.updataPenTime(penTime);
    }

}
