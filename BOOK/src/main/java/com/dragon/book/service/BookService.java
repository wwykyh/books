package com.dragon.book.service;

import java.util.List;


import com.dragon.book.model.TBorrow;

public interface BookService {

	public List<TBorrow> getBookTop();
	public List<TBorrow> getUserTop();
}
