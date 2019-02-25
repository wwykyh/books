package com.dragon.book.service.ebookService;

import com.dragon.book.model.TBorrow;
import com.dragon.book.model.TBorrowVo;

import java.util.List;
import java.util.Map;

/**
 * 归还审核接口
 */
public interface RevertCheckService {

    // 归还审核
    List<TBorrow> getTBorrowRevertList(Map filter);

    Integer getTBorrowRevertListCounts(Map filter);

    TBorrowVo getSingleRevertTBorrow(Integer id);

    boolean updateRevertTBorrowSh(Integer id, Integer sh);
}
