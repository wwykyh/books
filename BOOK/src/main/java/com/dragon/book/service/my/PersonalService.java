package com.dragon.book.service.my;

import com.dragon.book.mapper.my.PersonalDao;
import com.dragon.book.model.TBorrow;
import com.dragon.book.pojo.BookBorrow;
import com.dragon.book.pojo.Borrow;
import com.dragon.book.model.TSysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 我的：个人信息
 * zzm
 */
@Service
public class PersonalService {

    @Autowired
    private PersonalDao personalDao;

    /**
     * 获取用户信息
     * @param userid
     * @return
     */
    public TSysUser selectUserIndormation(int userid){
        TSysUser userinfo = personalDao.selectUserInformation(userid);
        return userinfo;
    }

    /**
     * 获取用户借书信息
     * @param userid
     * @return
     */
    public List<BookBorrow> selectBookInformation(int userid){
        List<BookBorrow> bookBorrow = personalDao.selectBookInformation(userid);
        return bookBorrow;
    }

    /**
     * 归还图书
     * @param isbn
     * @param userid
     */
    public void returnBook(String isbn,int userid){
//        long time = System.currentTimeMillis();
//        Date date = new Date(time);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String ghrq = dateFormat.format(date);
        personalDao.updateBorrow(isbn,userid);
    }

    /**
     * 续借图书
     * @param isbn
     * @param userid
     */
    public void renew(String isbn,int userid){
        personalDao.updatejszt(isbn,userid);
    }

    /**
     * 借阅详情
     * @param id
     * @return
     */
    public TBorrow borrowInfo(int id){
        return personalDao.selectBorrowInfo(id);
    }

}
