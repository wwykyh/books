package com.dragon.book.mapper;

import com.dragon.book.model.TBorrow;
import com.dragon.book.model.TBorrowVo;

import java.util.List;
import java.util.Map;

public interface CheckMapper {

    // 借阅审核
    List<TBorrow> getTBorrowCheckList(Map filter);

    TBorrow getSingleCheckTBorrow(Integer id);

    Integer getCounts(Map filter);

    // 归还审核
    List<TBorrow> getTBorrowRevertList(Map filter);

    Integer getTBorrowRevertListCounts(Map filter);

    TBorrowVo getSingleRevertTBorrow(Integer id);

    int updateRevertTBorrowSh(Map filter);
}
