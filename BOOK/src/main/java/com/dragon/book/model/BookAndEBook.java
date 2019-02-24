package com.dragon.book.model;

public class BookAndEBook {
	



	@Override
	public String toString() {
		return "BookAndEBook [id=" + id + ", isbn=" + isbn + ", sm=" + sm
				+ ", pubId=" + pubId + ", cbrq=" + cbrq + ", zz=" + zz
				+ ", typeId=" + typeId + ", tsdl=" + tsdl + ", rkrq=" + rkrq
				+ ", status=" + status + ", cs=" + cs + ", tPublish="
				+ tPublish + ", tType=" + tType + ", eBookId=" + eBookId
				+ ", eBookXm=" + eBookXm + ", wjdz=" + wjdz + ", ms=" + ms
				+ ", scsj=" + scsj + ", xzsj=" + xzsj + ", tszl=" + tszl
				+ ", userId=" + userId + "]";
	}

	private String id;

	private String isbn;

	private String sm;

	private String pubId;

	private String cbrq;

	private String zz;

	private Integer typeId;

	private String tsdl;

	private String rkrq;

	private Integer status;

	private Integer cs;
	
	private TPublish tPublish;
	
	private TType tType;

	
	private String eBookId;

    private String eBookXm;

   // private Integer typeId;

    private String wjdz;

    private String ms;

    private String scsj;

    private String xzsj;

    private String tszl;

    private Integer userId;
	
	
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

	public TPublish gettPublish() {
		return tPublish;
	}

	public void settPublish(TPublish tPublish) {
		this.tPublish = tPublish;
	}

	public TType gettType() {
		return tType;
	}

	public void settType(TType tType) {
		this.tType = tType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
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

	public String getPubId() {
		return pubId;
	}

	public void setPubId(String pubId) {
		this.pubId = pubId == null ? null : pubId.trim();
	}

	public String getCbrq() {
		return cbrq;
	}

	public void setCbrq(String cbrq) {
		this.cbrq = cbrq == null ? null : cbrq.trim();
	}

	public String getZz() {
		return zz;
	}

	public void setZz(String zz) {
		this.zz = zz == null ? null : zz.trim();
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTsdl() {
		return tsdl;
	}

	public void setTsdl(String tsdl) {
		this.tsdl = tsdl == null ? null : tsdl.trim();
	}

	public String getRkrq() {
		return rkrq;
	}

	public void setRkrq(String rkrq) {
		this.rkrq = rkrq == null ? null : rkrq.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCs() {
		return cs;
	}

	public void setCs(Integer cs) {
		this.cs = cs;
	}
}