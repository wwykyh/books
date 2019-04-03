package com.dragon.book.service.impl;

import com.dragon.book.mapper.BorrowMapper;
import com.dragon.book.mapper.UserMapper;
import com.dragon.book.mapper.my.OvertimePaymentDao;
import com.dragon.book.model.TBorrow;
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

    @Override
    public List<TBorrow> getUserBorrow(int userid) {
        List<TBorrow> books = userMapper.selectUserBorrow(userid);
        return books;
    }

    @Override
    public String getOverTimeUser() {
        String a = "";
        List<UserBorrow> users  = overtimePaymentDao.findOvertimeUser();
        for (int i=0;i<users.size();i++){
            a = a+users.get(i).gettSysUser().getBm()+users.get(i).gettSysUser().getXm()+"借的"+
                    users.get(i).getSm()+"逾期尚未归还，请尽快归还！！！&emsp;&emsp;&emsp;";
        }
        return a;
    }

    @Override
    public List<Borrow> getRankingList() {
        List<Borrow> users = borrowMapper.selectByUserTop();
        return users;
    }

    @Override
    public List<Borrow> getHotBooks() {
        List<Borrow> hotBooks = borrowMapper.selectByBookTop();
        return hotBooks;
    }

    @Override
    public List<Borrow> getNewBooks() {
        List<Borrow> newBooks = borrowMapper.selectByBookTop();
        return newBooks;
    }
}
