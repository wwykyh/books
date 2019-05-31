package com.dragon.book.service.impl;

import com.dragon.book.mapper.CheckMapper;
import com.dragon.book.mapper.TBorrowMapper;
import com.dragon.book.mapper.TStoreMapper;
import com.dragon.book.mapper.my.NewsDao;
import com.dragon.book.model.TBorrow;
import com.dragon.book.model.TStore;
import com.dragon.book.pojo.TBorrowInfo;
import com.dragon.book.service.ebookService.BorrowCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class BorrowCheckServiceImpl implements BorrowCheckService {

    @Autowired
    private CheckMapper checkMapper;  // 自己的新建的mapper

    @Autowired
    private TBorrowMapper tBorrowMapper;  // 生成的

    @Autowired
    private TStoreMapper tStoreMapper;

    @Autowired
    private NewsDao newsDao;

    @Override
    public TBorrowInfo getSingleTBorrow(Integer id) {
        return checkMapper.getSingleCheckTBorrow(id);
    }

    @Override
    public List<TBorrowInfo> getTBorrowCheckList(Map filter) {
        return checkMapper.getTBorrowCheckList(filter);
    }

    @Override
    public Integer getCounts(Map filter) {
        return checkMapper.getCounts(filter);
    }

    @Override
    public boolean updateTBorrow(TBorrow tBorrow) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String nowDate = format.format(date);
        TStore tStore = tStoreMapper.selectByPrimaryKey(tBorrow.getsId());
        // 修改图书的库存状态，1：在库，0：出库
        tStore.setStatus(0);
        tStoreMapper.updateByPrimaryKey(tStore);
        int a = tBorrowMapper.updateByPrimaryKeyWithBLOBs(tBorrow);
        newsDao.addBorrowNews(tBorrow.getUserId(),tStore.getIsbn(),nowDate);
        return  a> 0;
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
