package com.dragon.book.pojo;

import com.dragon.book.model.TBlackList;
import com.dragon.book.model.TSysUser;

public class BlackList extends TBlackList {
    TSysUser user;

    public TSysUser getUser() {
        return user;
    }

    public void setUser(TSysUser user) {
        this.user = user;
    }
}
