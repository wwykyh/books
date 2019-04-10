package com.dragon.book.mapper;

import java.util.List;

import com.dragon.book.model.BookAndEBook;
import com.dragon.book.model.TBook;
import com.dragon.book.model.TBorrow;
import com.dragon.book.pojo.Book;
import com.dragon.book.util.PageBean;
import org.apache.ibatis.annotations.Param;

public interface BookMapper {
	List<TBook> selectAllBook(PageBean pagebean);
	int getTotal(PageBean pagebean);
	List<Book> selectByDim(PageBean pagebean);
	List<Book> selectBooksByDim(PageBean pagebean);
	Book selectById(String id);
	List<BookAndEBook> selectEBookByDim(PageBean pagebean);
	
	public List<BookAndEBook> getEBookKey();
	public List<BookAndEBook> getBooksKey();
}
