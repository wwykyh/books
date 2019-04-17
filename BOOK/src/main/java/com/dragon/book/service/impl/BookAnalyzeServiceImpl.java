package com.dragon.book.service.impl;

import com.dragon.book.mapper.BookAnalyzeMapper;
import com.dragon.book.model.TBookAnalyze;
import com.dragon.book.model.TBorrow;
import com.dragon.book.service.BookAnalyzeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BookAnalyzeServiceImpl implements BookAnalyzeService {
    @Autowired
    private BookAnalyzeMapper bookAnalyzeMapper;

    public List<TBookAnalyze> getBoorowNum(String month) {
        TBorrow tBorrow = new TBorrow();
        //把获取到的月份转成int型
        int months = Integer.parseInt(month);
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new  SimpleDateFormat("yyyy-MM-");
        calendar.setTime(date);
        if(months==0){
            tBorrow.setJyrq(null);
            return bookAnalyzeMapper.selBorrowNum(tBorrow);
        }else{
            //修改成想要查看的月份
            calendar.set(Calendar.MONTH, months-1);
            date = calendar.getTime();
            tBorrow.setJyrq(dateFormat.format(date));
            return bookAnalyzeMapper.selBorrowNum(tBorrow);
        }
    }

    public BookAnalyzeMapper getBookAnalyzeMapper() {
        return bookAnalyzeMapper;
    }

    public void setBookAnalyzeMapper(BookAnalyzeMapper bookAnalyzeMapper) {
        this.bookAnalyzeMapper = bookAnalyzeMapper;
    }
}
