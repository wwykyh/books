package com.dragon.book.service.my.impl;


import com.dragon.book.mapper.my.OvertimePaymentDao;
import com.dragon.book.model.TBorrow;
import com.dragon.book.service.my.IOvertimePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;

/**
 * 超时归还
 */
@Service
public class OvertimePaymentServiceImpl implements IOvertimePaymentService {

    @Autowired
    private OvertimePaymentDao overtimePaymentDao;


    public List<TBorrow> findOvertimeBorrow(int uid) {
        return overtimePaymentDao.findOvertimeBorrow(uid);
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void addOvertimeNews() {
        List<TBorrow> tBorrowList = overtimePaymentDao.findAllOvertimeBorrow();
        String currentDate = LocalDate.now().toString();
        if (!CollectionUtils.isEmpty(tBorrowList)) {
            for (TBorrow tBorrow : tBorrowList) {
                overtimePaymentDao.addOvertimeNews(tBorrow.getUserId(), tBorrow.getsId(), currentDate);
            }
        }
    }

}
