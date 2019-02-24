package com.dragon.book.model;

public class TSysUser {
    @Override
	public String toString() {
		return "TSysUser [userId=" + userId + ", xm=" + xm + ", bm=" + bm
				+ ", pwd=" + pwd + ", lxfs=" + lxfs + ", email=" + email
				+ ", dz=" + dz + ", isadmin=" + isadmin + ", ishmd=" + ishmd
				+ ", isbn=" + isbn + ", kjtscs=" + kjtscs + ", cs=" + cs
				+ ", grsm=" + grsm + "]";
	}

	private Integer userId;

    private String xm;

    private String bm;

    private String pwd;

    private String lxfs;

    private String email;

    private String dz;

    private Integer isadmin;

    private Integer ishmd;

    private String isbn;

    private Integer kjtscs;

    private Integer cs;

    private String grsm;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm == null ? null : xm.trim();
    }

    public String getBm() {
        return bm;
    }

    public void setBm(String bm) {
        this.bm = bm == null ? null : bm.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getLxfs() {
        return lxfs;
    }

    public void setLxfs(String lxfs) {
        this.lxfs = lxfs == null ? null : lxfs.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getDz() {
        return dz;
    }

    public void setDz(String dz) {
        this.dz = dz == null ? null : dz.trim();
    }

    public Integer getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(Integer isadmin) {
        this.isadmin = isadmin;
    }

    public Integer getIshmd() {
        return ishmd;
    }

    public void setIshmd(Integer ishmd) {
        this.ishmd = ishmd;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn == null ? null : isbn.trim();
    }

    public Integer getKjtscs() {
        return kjtscs;
    }

    public void setKjtscs(Integer kjtscs) {
        this.kjtscs = kjtscs;
    }

    public Integer getCs() {
        return cs;
    }

    public void setCs(Integer cs) {
        this.cs = cs;
    }

    public String getGrsm() {
        return grsm;
    }

    public void setGrsm(String grsm) {
        this.grsm = grsm == null ? null : grsm.trim();
    }
}