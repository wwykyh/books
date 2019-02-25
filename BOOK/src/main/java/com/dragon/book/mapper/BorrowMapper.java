package com.dragon.book.mapper;

import java.util.List;
import java.util.Map;

import com.dragon.book.model.TBorrow;

public interface BorrowMapper {
	List<TBorrow> selectByUserTop();

	List<TBorrow> selectByBookTop();

	// 获取借阅未审核列表
	List<TBorrow> getTBorrowCheckList(Map filter);
    TBorrow getSingleCheckTBorrow(Integer id);
	Integer getCounts(Map filter);

	// 图书借阅归还
	List<TBorrow> getTBorrowRevertList(Map filter);
	Integer getTBorrowRevertListCounts(Map filter);
}
