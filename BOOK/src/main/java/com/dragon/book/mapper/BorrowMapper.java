package com.dragon.book.mapper;

import java.util.List;

import com.dragon.book.pojo.Borrow;

public interface BorrowMapper {
	List<Borrow> selectByUserTop();

	List<Borrow> selectByBookTop();

	List<Borrow> selectByBookNew();

	int returnBookRequest(String sId);
}
