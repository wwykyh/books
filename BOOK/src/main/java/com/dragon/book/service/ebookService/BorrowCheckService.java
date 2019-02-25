package com.dragon.book.service.ebookService;

import com.dragon.book.model.TBorrow;

import java.util.List;
import java.util.Map;

/**
 * 借阅审核接口
 */
public interface BorrowCheckService {

    TBorrow getSingleTBorrow(Integer id);

    List<TBorrow> getTBorrowCheckList(Map filter);

    Integer getCounts(Map filter);

    // 审核更新
    boolean updateTBorrow(TBorrow tBorrow);
}
