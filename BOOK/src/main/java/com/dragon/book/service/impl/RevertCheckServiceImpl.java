package com.dragon.book.service.impl;

import com.dragon.book.mapper.*;
import com.dragon.book.mapper.my.NewsDao;
import com.dragon.book.model.*;
import com.dragon.book.pojo.TBorrowInfo;
import com.dragon.book.service.BookManagerService;
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

    @Autowired
    private NewsDao newsDao;

    @Autowired
    private BookManagerService bookManagerService;

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

        SimpleDateFormat formatA = new SimpleDateFormat("yyyy-MM-dd");
        Date dateA = new Date();
        String nowDate = formatA.format(dateA);

        TBorrow tBorrow = tBorrowMapper.selectByPrimaryKey(id);
        tBorrow.setGhrq(nowDate);
        String sId = tBorrow.getsId();
        Map<String, Object> filter = new HashMap<>();
        filter.put("sId", sId);
        filter.put("sh", sh);
//        tBorrow.setJyzt(2);  // 2  代表的是归还状态
        tBorrow.setStatus(1); // 1 确认状态为确认通过
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
//            Date ghrq = format.parse(tBorrow.getGhrq());
            Date jhghrq = format.parse(tBorrow.getJhghrq());
            // 超时归还
            if (new Date().getTime() > jhghrq.getTime()) {
                TOvertime tOvertime = new TOvertime();
                tOvertime.setBookId(sId);
                tOvertime.setUserId(tBorrow.getUserId());
                tOvertimeMapper.insert(tOvertime);
                // 查询用户超时归还次数
                TOvertimeExample tOvertimeExample = new TOvertimeExample();
                TOvertimeExample.Criteria criteria = tOvertimeExample.createCriteria();
                criteria.andUserIdEqualTo(tBorrow.getUserId());
                int total = tOvertimeMapper.countByExample(tOvertimeExample);
                if (total >= 2) {
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
        boolean f;
        if ("1".equals(statusPay)) {
            Date date = new Date();
            format = new SimpleDateFormat("yyyy-MM-dd");
            String time = format.format(date);
            // System.out.println(true);
            TStore tStore = tStoreMapper.selectByPrimaryKey(sId);
            TCompensate tCompensate = new TCompensate();
            tCompensate.setIspc(0);
            tCompensate.setSh(sh);
            tCompensate.setUserId(tBorrow.getUserId());
            tCompensate.setPcdate(time);
            tCompensate.setsId(tStore.getIsbn());
            f = tCompensateMapper.insert(tCompensate) > 0;
        }
        boolean a = checkMapper.updateRevertTBorrowSh(filter) >= 0 &&
                tBorrowMapper.updateByPrimaryKey(tBorrow) > 0;
        TStore tStore = tStoreMapper.selectByPrimaryKey(tBorrow.getsId());
        newsDao.addRevertNews(tBorrow.getUserId(), tStore.getIsbn(), nowDate);
        return a;
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

    public TCompensateMapper gettCompensateMapper() {
        return tCompensateMapper;
    }

    public void settCompensateMapper(TCompensateMapper tCompensateMapper) {
        this.tCompensateMapper = tCompensateMapper;
    }

    public TStoreMapper gettStoreMapper() {
        return tStoreMapper;
    }

    public void settStoreMapper(TStoreMapper tStoreMapper) {
        this.tStoreMapper = tStoreMapper;
    }

    public TOvertimeMapper gettOvertimeMapper() {
        return tOvertimeMapper;
    }

    public void settOvertimeMapper(TOvertimeMapper tOvertimeMapper) {
        this.tOvertimeMapper = tOvertimeMapper;
    }

    public TSysUserMapper gettSysUserMapper() {
        return tSysUserMapper;
    }

    public void settSysUserMapper(TSysUserMapper tSysUserMapper) {
        this.tSysUserMapper = tSysUserMapper;
    }
}
