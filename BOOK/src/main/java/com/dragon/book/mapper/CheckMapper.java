package com.dragon.book.mapper;

import com.dragon.book.model.TBorrow;
import com.dragon.book.model.TBorrowVo;
import com.dragon.book.model.TSysUser;
import com.dragon.book.model.TSystemConfig;
import com.dragon.book.pojo.TBorrowInfo;

import java.util.List;
import java.util.Map;

public interface CheckMapper {

    //续借审核
    int updateTBorrowById(TBorrow tBorrow);

    List<TBorrowInfo> getTBorrowRenewList(Map filter);

    TBorrowInfo getSingleRenewTBorrow(Integer id);

    Integer getRenewCounts(Map filter);

    TSysUser getUser(int userId);

    TSystemConfig getSystemConfig();

    int updataBorrow(TBorrow tBorrow);

    // 借阅审核
    List<TBorrowInfo> getTBorrowCheckList(Map filter);

    TBorrowInfo getSingleCheckTBorrow(Integer id);

    Integer getCounts(Map filter);

    // 归还审核
    List<TBorrowInfo> getTBorrowRevertList(Map filter);

    Integer getTBorrowRevertListCounts(Map filter);

    TBorrowVo getSingleRevertTBorrow(Integer id);

    int updateRevertTBorrowSh(Map filter);
}
