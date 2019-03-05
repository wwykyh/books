package com.dragon.book.pojo;

import com.dragon.book.model.TBorrow;
import com.dragon.book.model.TPublish;
import com.dragon.book.model.TSysUser;

public class Borrow extends TBorrow {
    private TSysUser user;

    private TPublish publish;
    private  String cs;

    public TSysUser getUser() {
        return user;
    }

    public void setUser(TSysUser user) {
        this.user = user;
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
