package com.dragon.book.model;

public class BookAndEBook {

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String isbn;

	private String wz;

	private Integer sh;

	private String rksj;

	private Integer status;

	private Integer typeId;

	private String lxmc;

	private String sm;

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getLxmc() {
		return lxmc;
	}

	public void setLxmc(String lxmc) {
		this.lxmc = lxmc;
	}

	public String getSm() {
		return sm;
	}

	public void setSm(String sm) {
		this.sm = sm;
	}

	public String getCbsmc() {
		return cbsmc;
	}

	public void setCbsmc(String cbsmc) {
		this.cbsmc = cbsmc;
	}

	public String getCbrq() {
		return cbrq;
	}

	public void setCbrq(String cbrq) {
		this.cbrq = cbrq;
	}

	public String getZz() {
		return zz;
	}

	public void setZz(String zz) {
		this.zz = zz;
	}

	public String getTsdl() {
		return tsdl;
	}

	public void setTsdl(String tsdl) {
		this.tsdl = tsdl;
	}

	private String cbsmc;

	private String cbrq;

	private String zz;

	private String tsdl;

	private String eBookId;

	private String eBookXm;

	// private Integer typeId;

	private String wjdz;

	private String ms;

	private String scsj;

	private String xzsj;

	private String tszl;

	private Integer userId;

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getWz() {
		return wz;
	}

	public void setWz(String wz) {
		this.wz = wz;
	}

	public Integer getSh() {
		return sh;
	}

	public void setSh(Integer sh) {
		this.sh = sh;
	}

	public String getRksj() {
		return rksj;
	}

	public void setRksj(String rksj) {
		this.rksj = rksj;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}



	public String geteBookId() {
		return eBookId;
	}

	public void seteBookId(String eBookId) {
		this.eBookId = eBookId;
	}

	public String geteBookXm() {
		return eBookXm;
	}

	public void seteBookXm(String eBookXm) {
		this.eBookXm = eBookXm;
	}

	public String getWjdz() {
		return wjdz;
	}

	public void setWjdz(String wjdz) {
		this.wjdz = wjdz;
	}

	public String getMs() {
		return ms;
	}

	public void setMs(String ms) {
		this.ms = ms;
	}

	public String getScsj() {
		return scsj;
	}

	public void setScsj(String scsj) {
		this.scsj = scsj;
	}

	public String getXzsj() {
		return xzsj;
	}

	public void setXzsj(String xzsj) {
		this.xzsj = xzsj;
	}

	public String getTszl() {
		return tszl;
	}

	public void setTszl(String tszl) {
		this.tszl = tszl;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "BookAndEBook [id=" + id + ", isbn=" + isbn + ", wz=" + wz
				+ ", sh=" + sh + ", rksj=" + rksj + ", status=" + status
				+ ", typeId=" + typeId + ", lxmc=" + lxmc + ", sm=" + sm
				+ ", cbsmc=" + cbsmc + ", cbrq=" + cbrq + ", zz=" + zz
				+ ", tsdl=" + tsdl + ", eBookId=" + eBookId + ", eBookXm="
				+ eBookXm + ", wjdz=" + wjdz + ", ms=" + ms + ", scsj=" + scsj
				+ ", xzsj=" + xzsj + ", tszl=" + tszl + ", userId=" + userId
				+ "]";
	}


}