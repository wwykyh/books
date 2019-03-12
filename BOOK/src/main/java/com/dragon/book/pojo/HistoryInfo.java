package com.dragon.book.pojo;

import com.dragon.book.model.TBook;
import com.dragon.book.model.TBorrow;
import com.dragon.book.model.TPublish;
import com.dragon.book.model.TSysUser;

import java.io.Serializable;

/**
 * @ClassNamePublishInfo
 * @Description TODO
 * @Author liulei
 * @Date 2019/3/5
 */
public class HistoryInfo extends TBorrow implements Serializable {

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
