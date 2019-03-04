package com.dragon.book.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dragon.book.mapper.BookMapper;
import com.dragon.book.mapper.BorrowMapper;
import com.dragon.book.mapper.TBorrowMapper;
import com.dragon.book.model.BookAndEBook;
import com.dragon.book.model.TBook;
import com.dragon.book.model.TBorrow;
import com.dragon.book.service.BookService;
import com.dragon.book.util.PageBean;

@Service
public class BookServiceImpl implements BookService {

	final int Max = 100;

	@Autowired
	BorrowMapper borrowMapper;

	@Autowired
	TBorrowMapper tBorrowMapper;

	@Autowired
	BookMapper bookMapper;

	@Override
	public List<TBorrow> getBookTop() {
		// TODO Auto-generated method stub
		return borrowMapper.selectByBookTop();
	}

	@Override
	public List<TBorrow> getUserTop() {
		// TODO Auto-generated method stub
		return borrowMapper.selectByUserTop();
	}

	@Override
	public List<BookAndEBook> getBooks(PageBean pagebean) {
		// TODO Auto-generated method stub
		return bookMapper.selectByDim(pagebean);
	}

	@Override
	public List<BookAndEBook> getEBooks(PageBean pagebean) {
		// TODO Auto-generated method stub
		return bookMapper.selectEBookByDim(pagebean);
	}

	@Override
	public int getTotal(PageBean pagebean) {
		// TODO Auto-generated method stub
		return bookMapper.getTotal(pagebean);
	}

	@Override
	public TBook getBook(String id) {
		// TODO Auto-generated method stub
		return bookMapper.selectById(id);
	}

	@Override
	public List<BookAndEBook> getBooksKey() {
		// TODO Auto-generated method stub
		return bookMapper.getBooksKey();
	}

	@Override
	public List<BookAndEBook> getEBookKey() {
		// TODO Auto-generated method stub
		return bookMapper.getEBookKey();
	}

	@Override
	public int insertBorrow(TBorrow borrow) {
		// TODO Auto-generated method stub
		return tBorrowMapper.insertSelective(borrow);
	}

	@Override
	public String getKey(String wz, String tsdl) {
		// TODO Auto-generated method stub
		String id = "DBD";
		String st = null;

		if ("纸质".equals(tsdl)) {
			id = id + "Z";
		} else {
			id = id + "E";
		}

		long time = System.currentTimeMillis();
		Date date = new Date(time);
		String ma = "yyyyMMdd";

		SimpleDateFormat forma = new SimpleDateFormat(ma);
		String nwdate = forma.format(date);
		
		st = getDbKey();

		id = id + wz + nwdate + st;
		
		return id;
	}

	@Override
	public String getDbKey() {
		// TODO Auto-generated method stub

		try {
			List<BookAndEBook> ebookKey = getEBookKey();
			List<BookAndEBook> bookKey = getBooksKey();
			List<BookAndEBook> allKey = new ArrayList<BookAndEBook>();
			allKey.addAll(bookKey);
			allKey.addAll(ebookKey);
			HashSet<String> set = new HashSet<String>();
			for (BookAndEBook b : allKey) {
				String str = b.getId().substring(b.getId().length() - 3);
				set.add(str);
			}
			Random rd = new Random();
			Boolean flag = true;
			do {
				int t = rd.nextInt(Max);
				String st = "" + t;
				if (!set.contains(st)) {
					if (st.length() == 1) {
						st = "00" + st;
					}
					if (st.length() == 2) {
						st = "0" + st;
					}
				
					return st;
				}
			} while (flag);
		} catch (NullPointerException e) {
			System.out.println(e);
		}

		return null;
	}

	@Override
	public String getTime(String jyrq, String jhghrq) {
		// TODO Auto-generated method stub
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = sdf.parse(jyrq);// 字符串转换为data
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, Integer.parseInt(jhghrq));
		String time = sdf.format(cal.getTime());// data转换为字符串
		return time;
	}
	
	@Override
	public List<BookAndEBook> joinBook(List<BookAndEBook> booksList,
			List<BookAndEBook> EBookslist) {
		// TODO Auto-generated method stub

		List<BookAndEBook> books = new ArrayList<>();
		books.addAll(booksList);
		books.addAll(EBookslist);
		return books;
	}
	public BorrowMapper getBorrowMapper() {
		return borrowMapper;
	}

	public void setBorrowMapper(BorrowMapper borrowMapper) {
		this.borrowMapper = borrowMapper;
	}

	public BookMapper getBookMapper() {
		return bookMapper;
	}

	public void setBookMapper(BookMapper bookMapper) {
		this.bookMapper = bookMapper;
	}

}
