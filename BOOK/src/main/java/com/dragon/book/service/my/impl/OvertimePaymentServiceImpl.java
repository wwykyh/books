package com.dragon.book.service.my.impl;


import com.dragon.book.mapper.my.OvertimePaymentDao;
import com.dragon.book.model.TBorrow;
import com.dragon.book.service.my.IOvertimePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

}
