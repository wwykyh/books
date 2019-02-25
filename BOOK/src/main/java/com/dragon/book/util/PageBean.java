package com.dragon.book.util;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassNamepageResulte
 * @Description TODO
 * @Author liulei
 * @Date 2019/1/28
 */
public class PageBean implements Serializable {
	private Integer page;// 当前页

	private Integer pagesize; // 页面大小
	private List rows;// 数据源
	private Integer total;// 总数

	private String dim; // 书名、作者
	private String startTime;// 开始时间
	private String endTime;// 结束时间
	private String s_tsdl;// 图书大类
	private String s_type;// 图书类型

	@Override
	public String toString() {
		return "PageBean [page=" + page + ", pagesize=" + pagesize + ", rows="
				+ rows + ", total=" + total + ", dim=" + dim + ", startTime="
				+ startTime + ", endTime=" + endTime + ", s_tsdl=" + s_tsdl
				+ ", s_type=" + s_type + "]";
	}

	public String getS_tsdl() {
		return s_tsdl;
	}

	public void setS_tsdl(String s_tsdl) {
		this.s_tsdl = s_tsdl;
	}

	public String getS_type() {
		return s_type;
	}

	public void setS_type(String s_type) {
		this.s_type = s_type;
	}

	public String getDim() {
		return dim;
	}

	public void setDim(String dim) {
		this.dim = dim;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPagesize() {
		return pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
}
