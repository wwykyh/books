package com.dragon.book.service.my.impl;

import com.dragon.book.mapper.my.PersonalDao;
import com.dragon.book.model.TBorrow;
import com.dragon.book.model.TSysUser;
import com.dragon.book.pojo.BookBorrow;
import com.dragon.book.service.my.IPersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 我的：个人信息
 * zzm
 */
@Service
public class PersonalServiceImpl implements IPersonalService {

    @Autowired
    private PersonalDao personalDao;

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    public TSysUser selectUserIndormation(int userId) {
        TSysUser userinfo = personalDao.selectUserInformation(userId);
        return userinfo;
    }

    /**
     * 获取用户借书信息
     *
     * @param userId
     * @return
     */
    public List<BookBorrow> selectBookInformation(int userId) {
        List<BookBorrow> bookBorrow = personalDao.selectBookInformation(userId);
        return bookBorrow;
    }

    /**
     * 归还图书
     *
     * @param isbn
     * @param userId
     */
    public void returnBook(String isbn, int userId) {
//        long time = System.currentTimeMillis();
//        Date date = new Date(time);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String ghrq = dateFormat.format(date);
        personalDao.updateBorrow(isbn, userId);
    }

    /**
     * 续借图书
     *
     * @param isbn
     * @param userId
     */
    public void renew(String isbn, int userId) {
        personalDao.updatejszt(isbn, userId);
    }

    /**
     * 借阅详情
     *
     * @param id
     * @return
     */
    public TBorrow borrowInfo(int id) {
        return personalDao.selectBorrowInfo(id);
    }

}
