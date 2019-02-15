package com.dragon.book.mapper;

import java.util.List;

import com.dragon.book.model.TBorrow;

public interface BorrowMapper {
	List<TBorrow> selectByUserTop();

	List<TBorrow> selectByBookTop();
}
