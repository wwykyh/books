package com.dragon.book.service.impl;

import com.dragon.book.mapper.CheckMapper;
import com.dragon.book.mapper.TBorrowMapper;
import com.dragon.book.mapper.TStoreMapper;
import com.dragon.book.model.TBorrow;
import com.dragon.book.model.TBorrowVo;
import com.dragon.book.model.TStore;
import com.dragon.book.model.TStoreExample;
import com.dragon.book.service.ebookService.RevertCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RevertCheckServiceImpl implements RevertCheckService {

    @Autowired
    private CheckMapper checkMapper;  // 自己的新建的mapper

    @Autowired
    private TBorrowMapper tBorrowMapper;  // 生成的


    @Override
    public List<TBorrow> getTBorrowRevertList(Map filter) {
        return checkMapper.getTBorrowRevertList(filter);
    }

    @Override
    public Integer getTBorrowRevertListCounts(Map filter) {
        return checkMapper.getTBorrowRevertListCounts(filter);
    }

    @Override
    public TBorrowVo getSingleRevertTBorrow(Integer id) {
        return checkMapper.getSingleRevertTBorrow(id);
    }

    @Override
    public boolean updateRevertTBorrowSh(Integer id, Integer sh) {
        TBorrow tBorrow = tBorrowMapper.selectByPrimaryKey(id);
        String isbn = tBorrow.getsId();
        Map<String,Object> filter = new HashMap<>();
        filter.put("isbn",isbn);
        filter.put("sh", sh);
        tBorrow.setJyzt(2);  // 2  代表的是归还状态；tBorrow.setJyzt(3); 3 代表超时归还
       //  tBorrowMapper.updateByPrimaryKey(tBorrow);
        return checkMapper.updateRevertTBorrowSh(filter) >= 0 && tBorrowMapper.updateByPrimaryKey(tBorrow) > 0;
    }
}