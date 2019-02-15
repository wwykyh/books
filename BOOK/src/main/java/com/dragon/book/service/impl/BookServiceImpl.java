package com.dragon.book.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dragon.book.mapper.BorrowMapper;
import com.dragon.book.model.TBorrow;
import com.dragon.book.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	BorrowMapper bookMapper;
	
	@Override
	public List<TBorrow> getBookTop() {
		// TODO Auto-generated method stub
		
		List<TBorrow> bookList= bookMapper.selectByBookTop();
		
		return bookList;
	}
	
	@Override
	public List<TBorrow> getUserTop() {
		// TODO Auto-generated method stub
		
		List<TBorrow> userList= bookMapper.selectByUserTop();
		
		return userList;
	}
}
