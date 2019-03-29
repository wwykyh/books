package com.dragon.book.pojo;

import com.dragon.book.model.TBook;
import com.dragon.book.model.TBorrow;
import com.dragon.book.model.TSysUser;

/**
 * 超时归还POJO Tbook+Tborrow
 * zzm
 */

public class UserBorrow extends TBorrow {
    private TSysUser tSysUser;

    public TSysUser gettSysUser() {
        return tSysUser;
    }

    public void settSysUser(TSysUser tSysUser) {
        this.tSysUser = tSysUser;
    }
}
