package com.dragon.book.service.impl;

import com.dragon.book.mapper.CheckMapper;
import com.dragon.book.mapper.TBorrowMapper;
import com.dragon.book.model.TBorrow;
import com.dragon.book.pojo.TBorrowInfo;
import com.dragon.book.service.ebookService.BorrowCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class BorrowCheckServiceImpl implements BorrowCheckService {

    @Autowired
    private CheckMapper checkMapper;  // 自己的新建的mapper

    @Autowired
    private TBorrowMapper tBorrowMapper;  // 生成的

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
        return tBorrowMapper.updateByPrimaryKeyWithBLOBs(tBorrow) > 0;
    }
}
