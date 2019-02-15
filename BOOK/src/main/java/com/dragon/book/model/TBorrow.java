package com.dragon.book.model;

import java.util.Date;

public class TBorrow {
	private Integer id;

	private String isbn;

	private String sm;

	private Integer userId;

	private String lxfs;

	private Date jyrq;

	private Date jhghrq;

	private Date xjrq;

	private Date ghrq;

	private Integer status;

	private Integer jyzt;

	private String bz;

	private TSysUser user;
	
	private int cs;

	public TSysUser getUser() {
		return user;
	}

	public void setUser(TSysUser user) {
		this.user = user;
	}

	public int getCs() {
		return cs;
	}

	public void setCs(int cs) {
		this.cs = cs;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn == null ? null : isbn.trim();
	}

	public String getSm() {
		return sm;
	}

	public void setSm(String sm) {
		this.sm = sm == null ? null : sm.trim();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getLxfs() {
		return lxfs;
	}

	public void setLxfs(String lxfs) {
		this.lxfs = lxfs == null ? null : lxfs.trim();
	}

	public Date getJyrq() {
		return jyrq;
	}

	public void setJyrq(Date jyrq) {
		this.jyrq = jyrq;
	}

	public Date getJhghrq() {
		return jhghrq;
	}

	public void setJhghrq(Date jhghrq) {
		this.jhghrq = jhghrq;
	}

	public Date getXjrq() {
		return xjrq;
	}

	public void setXjrq(Date xjrq) {
		this.xjrq = xjrq;
	}

	public Date getGhrq() {
		return ghrq;
	}

	public void setGhrq(Date ghrq) {
		this.ghrq = ghrq;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getJyzt() {
		return jyzt;
	}

	public void setJyzt(Integer jyzt) {
		this.jyzt = jyzt;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz == null ? null : bz.trim();
	}
}