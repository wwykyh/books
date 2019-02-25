package com.dragon.book.service;

import java.util.List;

import com.dragon.book.model.BookAndEBook;
import com.dragon.book.model.TBook;
import com.dragon.book.model.TBorrow;
import com.dragon.book.util.PageBean;

public interface BookService {

	/**
	 * 获取图书排行榜
	 * 
	 * @return 检索的结果集合
	 */
	public List<TBorrow> getBookTop();

	/**
	 * 获取用户排行榜
	 * 
	 * @return 检索的结果集合
	 */
	public List<TBorrow> getUserTop();

	/**
	 * 获取纸质图书集合
	 * 
	 * @param pagebean
	 *            搜索类
	 * @return 检索的结果集合
	 */
	public List<BookAndEBook> getBooks(PageBean pagebean);

	/**
	 * 获取查询的总数，用于分页的total
	 * 
	 * @param pagebean
	 * @return 检索的结果集合
	 */
	public int getTotal(PageBean pagebean);

	/**
	 * 获取图书的详细信息
	 * 
	 * @param id
	 *            图书id
	 * @return 检索结果实体
	 */
	public TBook getBook(String id);

	/**
	 * 插入图书的借阅记录
	 * 
	 * @param borrow
	 * @return 图书的详细信息
	 */
	public int insertBorrow(TBorrow borrow);

	/**
	 * 根据参数检索图书
	 * 
	 * @param pagebean
	 *            搜索类
	 * @return 返回检索的集合
	 */
	public List<BookAndEBook> getEBooks(PageBean pagebean);

	/**
	 * 获取电子书的主键
	 * 
	 * @return 主键集合
	 */
	public List<BookAndEBook> getEBookKey();

	/**
	 * 获取纸质图书的主键
	 * 
	 * @return 主键集合
	 */
	public List<BookAndEBook> getBooksKey();

	/**
	 * 生成图书的主键
	 * 
	 * @param wz
	 *            图书的位置，电纸书为 00;
	 * @param tsdl
	 *            图书大类
	 * @return 图书主键
	 */
	public String getKey(String wz, String tsdl);

	/**
	 * 生成图书后面三位序号
	 * 
	 * @return 序号，三位数 eg：001
	 */
	public String getDbKey();

	/**
	 * 计算计划归还日期
	 * 
	 * @param jyrq
	 *            借阅日期
	 * @param jhghrq
	 *            借阅周期
	 * @return
	 */
	public String getTime(String jyrq, String jhghrq);
	
	/**
	 * 将book表和Ebook查询出的数据拼接成一个集合，给json传递到前台页面
	 * 
	 * @param booksList
	 *            book表的数据集合
	 * @param EBookslist
	 *            ebook表的数据集合
	 * @return
	 */
	public List<BookAndEBook> joinBook(List<BookAndEBook> booksList,
			List<BookAndEBook> EBookslist);
}
