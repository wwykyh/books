package com.dragon.book.pojo;

import com.dragon.book.model.TBook;
import com.dragon.book.model.TCompensate;
import com.dragon.book.model.TSysUser;

import java.io.Serializable;

/**
 * @ClassNamePcInfo
 * @Description TODO
 * @Author wangnan
 * @Date 2019/3/9
 */
public class PcInfo extends TCompensate implements Serializable {

    private TBook book ;
    private TSysUser user ;

    public TBook getBook() {
        return book;
    }

    public void setBook(TBook book) {
        this.book = book;
    }

    public TSysUser getUser() {
        return user;
    }

    public void setUser(TSysUser user) {
        this.user = user;
    }
}
