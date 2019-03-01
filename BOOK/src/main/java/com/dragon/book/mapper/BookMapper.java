package com.dragon.book.mapper;

import java.util.List;

import com.dragon.book.model.BookAndEBook;
import com.dragon.book.model.TBook;
import com.dragon.book.pojo.Book;
import com.dragon.book.util.PageBean;

public interface BookMapper {
	List<TBook> selectAllBook(PageBean pagebean);
	int getTotal(PageBean pagebean);
	List<BookAndEBook> selectByDim(PageBean pagebean);
	Book selectById(String id);
	List<BookAndEBook> selectEBookByDim(PageBean pagebean);
	
	public List<BookAndEBook> getEBookKey();
	public List<BookAndEBook> getBooksKey();
}
