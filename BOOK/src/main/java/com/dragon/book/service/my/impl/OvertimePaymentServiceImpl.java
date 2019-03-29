package com.dragon.book.service.my.impl;


import com.dragon.book.mail.DoSendEmail;
import com.dragon.book.mapper.my.OvertimePaymentDao;
import com.dragon.book.model.TBorrow;
import com.dragon.book.pojo.UserBorrow;
import com.dragon.book.service.my.IOvertimePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *超时归还
 */
@Service
public class OvertimePaymentServiceImpl implements IOvertimePaymentService {

    @Autowired
    private OvertimePaymentDao overtimePaymentDao;


    public List<TBorrow> findOvertimeBorrow(int uid){
//        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String currentTime = timeFormat.format(new Date());
        return overtimePaymentDao.findOvertimeBorrow(uid);
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void findOvertimeUser() {
       List<UserBorrow> userBorrows = overtimePaymentDao.findOvertimeUser();
        DoSendEmail doSendEmail = new DoSendEmail();
       if(null!=userBorrows){
           for(int i=0;i<userBorrows.size();i++){
               String bookName = userBorrows.get(i).getSm();
               String mail = userBorrows.get(i).gettSysUser().getEmail();
               String userName = userBorrows.get(i).gettSysUser().getXm();
               doSendEmail.sendEmail(userName,bookName, mail);
           }
       }
    }
}
