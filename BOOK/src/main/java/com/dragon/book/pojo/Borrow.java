package com.dragon.book.pojo;

import com.dragon.book.model.TBook;
import com.dragon.book.model.TBorrow;
import com.dragon.book.model.TPublish;
import com.dragon.book.model.TSysUser;

public class Borrow extends TBorrow {
    private TSysUser user;
    private TBook book;
    private TPublish publish;
    private  String cs;
    private  String rksj;

    public TSysUser getUser() {
        return user;
    }

    public void setUser(TSysUser user) {
        this.user = user;
    }

    public TBook getBook() {
        return book;
    }

    public void setBook(TBook book) {
        this.book = book;
    }

    public String getRksj() {
        return rksj;
    }

    public void setRksj(String rksj) {
        this.rksj = rksj;
    }

    public TPublish getPublish() {
        return publish;
    }

    public void setPublish(TPublish publish) {
        this.publish = publish;
    }

    public String getCs() {
        return cs;
    }

    public void setCs(String cs) {
        this.cs = cs;
    }
}
