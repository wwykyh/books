package com.dragon.book.pojo;

import com.dragon.book.model.TBlackList;
import com.dragon.book.model.TSysUser;

public class BlackList extends TBlackList {
    TSysUser tSysUser;

    public TSysUser gettSysUser() {
        return tSysUser;
    }

    public void settSysUser(TSysUser tSysUser) {
        this.tSysUser = tSysUser;
    }
}
