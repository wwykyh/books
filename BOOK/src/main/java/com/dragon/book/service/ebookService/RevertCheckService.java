package com.dragon.book.service.ebookService;

import com.dragon.book.model.TBorrowVo;
import com.dragon.book.pojo.TBorrowInfo;

import java.util.List;
import java.util.Map;

/**
 * 归还审核接口
 */
public interface RevertCheckService {

    // 归还审核
    List<TBorrowInfo> getTBorrowRevertList(Map filter);

    Integer getTBorrowRevertListCounts(Map filter);

    TBorrowVo getSingleRevertTBorrow(Integer id);

    boolean updateRevertTBorrowSh(Integer id, Integer sh, String statusPay);
}
