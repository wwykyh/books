package com.dragon.book.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.dragon.book.model.BookAndEBook;
import com.dragon.book.service.BookService;

@Component
public class GenerateId {

	@Autowired
	 BookService bookService;

	final  int Max = 100;
	int temp = Max;

	public String getId(String wz, String rkrq, String tsdl) {

		String id = "DBD";
		if ("纸质".equals(tsdl)) {
			id = id + "Z";
		} else {
			id = id + "E";
		}
		id = id + wz + getTime();
		return id;
	}

	public  String getkey() {

		try {
			List<BookAndEBook> ebookKey = bookService.getEBookKey();
			List<BookAndEBook> bookKey = bookService.getBooksKey();
			List<BookAndEBook> allKey = joinBook(bookKey, ebookKey);
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

	/**
	 * 将book表和Ebook查询出的数据拼接成一个集合，给json传递到前台页面
	 * 
	 * @param booksList
	 *            book表的数据集合
	 * @param EBookslist
	 *            ebook表的数据集合
	 * @return
	 */
	private  List<BookAndEBook> joinBook(List<BookAndEBook> booksList,
			List<BookAndEBook> EBookslist) {
		List<BookAndEBook> books = new ArrayList<>();
		books.addAll(booksList);
		books.addAll(EBookslist);
		////////
		booksList.addAll(EBookslist);
		return books;
	}

	/**
	 * 获取系统时间，并转换为yyyMMdd格式
	 * 
	 * @return
	 */
	public String getTime() {
		long time = System.currentTimeMillis();
		Date date = new Date(time);
		String ma = "yyyyMMdd";

		SimpleDateFormat forma = new SimpleDateFormat(ma);
		String nwdate = forma.format(date);
		return nwdate;
	}

}
