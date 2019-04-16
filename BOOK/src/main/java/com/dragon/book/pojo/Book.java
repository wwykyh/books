package com.dragon.book.pojo;

import com.dragon.book.model.TBook;
import com.dragon.book.model.TStore;
import com.dragon.book.model.TType;

public class Book extends TStore {
    private TType tType;

    private TBook tBook;

    @Override
    public String toString() {
        return "Book{" +
                "tType=" + tType +
                ", tBook=" + tBook +
                '}';
    }

    public TBook gettBook() {
        return tBook;
    }

    public void settBook(TBook tBook) {
        this.tBook = tBook;
    }

    public TType gettType() {
        return tType;
    }

    public void settType(TType tType) {
        this.tType = tType;
    }


}
