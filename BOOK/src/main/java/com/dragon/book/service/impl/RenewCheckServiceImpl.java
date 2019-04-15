package com.dragon.book.service.impl;

import com.dragon.book.mapper.CheckMapper;
import com.dragon.book.mapper.TBorrowMapper;
import com.dragon.book.mapper.TStoreMapper;
import com.dragon.book.mapper.my.NewsDao;
import com.dragon.book.mapper.my.PersonalDao;
import com.dragon.book.model.TBorrow;
import com.dragon.book.model.TSysUser;
import com.dragon.book.model.TSystemConfig;
import com.dragon.book.pojo.TBorrowInfo;
import com.dragon.book.service.ebookService.IRenewCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RenewCheckServiceImpl implements IRenewCheckService {

    @Autowired
    private CheckMapper checkMapper;

    @Autowired
    private TBorrowMapper tBorrowMapper;

    @Autowired
    private TStoreMapper tStoreMapper;

    @Autowired
    private PersonalDao personalDao;

    @Autowired
    private NewsDao newsDao;

    @Override
    public TBorrowInfo getSingleRenewTBorrow(Integer id) {
        return checkMapper.getSingleRenewTBorrow(id);
    }

    @Override
    public List<TBorrowInfo> getTBorrowRenewList(Map filter) {
        return checkMapper.getTBorrowRenewList(filter);
    }

    @Override
    public Integer getRenewCounts(Map filter) {
        return checkMapper.getRenewCounts(filter);
    }

    public TBorrow selectBorrowInfo(int id) {
        return personalDao.selectBorrowInfo(id);
    }


    public boolean updateTBorrow(int id, int uId, String jhghrq, String isbn) {

        TBorrow tBorrow = new TBorrow();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String xjrq = format.format(date);

        tBorrow.setId(id);
        tBorrow.setJyzt(1);
        tBorrow.setXjrq(xjrq);

        TSysUser tSysUser = checkMapper.getUser(uId);
        TSystemConfig tSystemConfig = checkMapper.getSystemConfig();
        try {
            Date currdate = format.parse(jhghrq);
            Calendar ca = Calendar.getInstance();
            ca.setTime(currdate);
            if (tSysUser.getIsadmin() == 1) {
                ca.add(Calendar.DATE, tSystemConfig.getBookTime() / 2);
                date = ca.getTime();
                String newjhghrq = format.format(date);
                tBorrow.setJhghrq(newjhghrq);
            } else if (tSysUser.getIsadmin() == 0) {
                ca.add(Calendar.DATE, tSystemConfig.getBookTime() / 2);
                date = ca.getTime();
                String newjhghrq = format.format(date);
                tBorrow.setJhghrq(newjhghrq);
            }
            newsDao.addRenewNews(uId, isbn, xjrq);
            return checkMapper.updateTBorrowById(tBorrow) > 0;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public CheckMapper getCheckMapper() {
        return checkMapper;
    }

    public void setCheckMapper(CheckMapper checkMapper) {
        this.checkMapper = checkMapper;
    }

    public TBorrowMapper gettBorrowMapper() {
        return tBorrowMapper;
    }

    public void settBorrowMapper(TBorrowMapper tBorrowMapper) {
        this.tBorrowMapper = tBorrowMapper;
    }

    public TStoreMapper gettStoreMapper() {
        return tStoreMapper;
    }

    public void settStoreMapper(TStoreMapper tStoreMapper) {
        this.tStoreMapper = tStoreMapper;
    }
}
