package com.dragon.book.service.impl;

import com.dragon.book.mapper.CheckMapper;
import com.dragon.book.mapper.TBorrowMapper;
import com.dragon.book.mapper.TStoreMapper;
import com.dragon.book.model.TBorrow;
import com.dragon.book.model.TStore;
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


    public boolean updateTBorrow(TBorrow tBorrow, String id, String status, String bz) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String xjrq = format.format(date);

        tBorrow.setStatus(1);
        tBorrow.setXjrq(xjrq);
        tBorrow.setBz(bz);

        TSysUser tSysUser = checkMapper.getUser(tBorrow.getUserId());
        TSystemConfig tSystemConfig = checkMapper.getSystemConfig();
        Calendar ca = Calendar.getInstance();
        if (tSysUser.getIsadmin() == 1) {
            ca.add(Calendar.DATE, tSystemConfig.getAdminTime() / 2);
            date = ca.getTime();
            String jhghrq = format.format(date);
            tBorrow.setJhghrq(jhghrq);
        } else if (tSysUser.getIsadmin() == 0) {
            ca.add(Calendar.DATE, tSystemConfig.getBookTime() / 2);
            date = ca.getTime();
            String jhghrq = format.format(date);
            tBorrow.setJhghrq(jhghrq);
        }
        return tBorrowMapper.updateByPrimaryKeyWithBLOBs(tBorrow) > 0;
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
