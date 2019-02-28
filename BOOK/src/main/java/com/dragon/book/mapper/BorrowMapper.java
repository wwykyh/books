package com.dragon.book.mapper;

import com.dragon.book.model.TBorrow;

import java.util.List;

public interface BorrowMapper {
	List<TBorrow> selectByUserTop();

	List<TBorrow> selectByBookTop();
}
