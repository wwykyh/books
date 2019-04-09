package com.dragon.book.service.impl;

import com.dragon.book.mapper.*;
import com.dragon.book.model.*;
import com.dragon.book.pojo.TBorrowInfo;
import com.dragon.book.service.ebookService.RevertCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Autowired
    private TOvertimeMapper tOvertimeMapper;

    @Autowired
    private TSysUserMapper tSysUserMapper;

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

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean updateRevertTBorrowSh(Integer id, Integer sh, String statusPay) {
        TBorrow tBorrow = tBorrowMapper.selectByPrimaryKey(id);
        String sId = tBorrow.getsId();
        Map<String,Object> filter = new HashMap<>();
        filter.put("sId",sId);
        filter.put("sh", sh);
        tBorrow.setJyzt(2);  // 2  代表的是归还状态；tBorrow.setJyzt(3); 3 代表超时归还
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date ghrq = format.parse(tBorrow.getGhrq());
            Date jhghrq = format.parse(tBorrow.getJhghrq());
            // 超时归还
            if (ghrq.getTime() > jhghrq.getTime()){
                TOvertime tOvertime = new TOvertime();
                tOvertime.setBookId(Integer.parseInt(sId));
                tOvertime.setUserId(tBorrow.getUserId());
                tOvertimeMapper.insert(tOvertime);
                // 查询用户超时归还次数
                TOvertimeExample tOvertimeExample = new TOvertimeExample();
                TOvertimeExample.Criteria criteria = tOvertimeExample.createCriteria();
                criteria.andUserIdEqualTo(tBorrow.getUserId());
                int total = tOvertimeMapper.countByExample(tOvertimeExample);
                if(total >= 2){
                    TSysUser tSysUser = tSysUserMapper.selectByPrimaryKey(tBorrow.getUserId());
                    tSysUser.setIshmd(1);
                    int f = tSysUserMapper.updateByPrimaryKey(tSysUser);
                    // 更新完之后删除表中记录
                    tOvertimeMapper.deleteByExample(tOvertimeExample);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
