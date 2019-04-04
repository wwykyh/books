package com.dragon.book.service.impl;

import com.dragon.book.mapper.CheckMapper;
import com.dragon.book.mapper.TBorrowMapper;
import com.dragon.book.mapper.TCompensateMapper;
import com.dragon.book.mapper.TStoreMapper;
import com.dragon.book.model.TBorrow;
import com.dragon.book.model.TBorrowVo;
import com.dragon.book.model.TCompensate;
import com.dragon.book.model.TStore;
import com.dragon.book.pojo.TBorrowInfo;
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

    @Autowired
    private TCompensateMapper tCompensateMapper;

    @Autowired
    private TStoreMapper tStoreMapper;

    @Override
    public List<TBorrowInfo> getTBorrowRevertList(Map filter) {
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
    public boolean updateRevertTBorrowSh(Integer id, Integer sh, String statusPay) {
        TBorrow tBorrow = tBorrowMapper.selectByPrimaryKey(id);
        String sId = tBorrow.getsId();
        Map<String,Object> filter = new HashMap<>();
        filter.put("sId",sId);
        filter.put("sh", sh);
        tBorrow.setJyzt(2);  // 2  代表的是归还状态；tBorrow.setJyzt(3); 3 代表超时归还
        // 更新赔偿表
        TStore tStore = tStoreMapper.selectByPrimaryKey(sId);
        TCompensate tCompensate = new TCompensate();
        tCompensate.setIspc(Integer.parseInt(statusPay));
        tCompensate.setSh(sh);
        tCompensate.setUserId(tBorrow.getUserId());
        tCompensate.setsId(tStore.getIsbn());
        return checkMapper.updateRevertTBorrowSh(filter) >= 0 &&
                tBorrowMapper.updateByPrimaryKey(tBorrow) > 0 &&
                tCompensateMapper.insert(tCompensate) > 0;
    }
}
