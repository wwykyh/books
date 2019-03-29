package com.dragon.book.pojo;

import com.dragon.book.model.TBook;
import com.dragon.book.model.TBorrow;

/**
 * 借阅信息POJO
 * zzm
 */
public class BookBorrow extends TBook {
    private TBorrow tBorrow;

    public TBorrow gettBorrow() {
        return tBorrow;
    }

    public void settBorrow(TBorrow tBorrow) {
        this.tBorrow = tBorrow;
    }
}
