package com.dragon.book.mapper;

import java.util.List;

import com.dragon.book.pojo.Borrow;
import org.apache.ibatis.annotations.Param;

public interface BorrowMapper {
	List<Borrow> selectByUserTop();

	List<Borrow> selectByBookTop();

	List<Borrow> selectByBookNew();

	int returnBookRequest(String sId);

	 int getBorrow(@Param("id") int id, @Param("status") int status);

}
