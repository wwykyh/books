package com.dragon.book.service.ebookService;

import com.dragon.book.model.TBorrow;
import com.dragon.book.pojo.TBorrowInfo;

import java.util.List;
import java.util.Map;

/**
 * 借阅确认接口
 */
public interface BorrowCheckService {

    TBorrowInfo getSingleTBorrow(Integer id);

    List<TBorrowInfo> getTBorrowCheckList(Map filter);

    Integer getCounts(Map filter);

    // 确认更新
    boolean updateTBorrow(TBorrow tBorrow);
}
