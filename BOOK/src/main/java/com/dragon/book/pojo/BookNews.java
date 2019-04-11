package com.dragon.book.pojo;

import com.dragon.book.model.TBook;
import com.dragon.book.model.TBookNews;

public class BookNews  extends TBookNews {
    private TBook tBook;

    public TBook gettBook() {
        return tBook;
    }

    public void settBook(TBook tBook) {
        this.tBook = tBook;
    }
}
