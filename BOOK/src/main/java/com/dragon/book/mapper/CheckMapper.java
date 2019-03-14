package com.dragon.book.mapper;

import com.dragon.book.model.TBorrowVo;
import com.dragon.book.pojo.TBorrowInfo;

import java.util.List;
import java.util.Map;

public interface CheckMapper {

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
