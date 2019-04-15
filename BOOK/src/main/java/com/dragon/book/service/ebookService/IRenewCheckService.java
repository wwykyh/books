package com.dragon.book.service.ebookService;

import com.dragon.book.model.TBorrow;
import com.dragon.book.model.TSystemConfig;
import com.dragon.book.pojo.TBorrowInfo;

import java.util.List;
import java.util.Map;

public interface IRenewCheckService {

    TBorrowInfo getSingleRenewTBorrow(Integer id);

    List<TBorrowInfo> getTBorrowRenewList(Map filter);

    Integer getRenewCounts(Map filter);

    // 审核更新
    boolean updateTBorrow(int id, int uId ,String jhghrq,String isbn);

    public TBorrow selectBorrowInfo(int id);

}
