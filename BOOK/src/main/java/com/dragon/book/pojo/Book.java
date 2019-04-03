package com.dragon.book.pojo;

import com.dragon.book.model.TBook;
import com.dragon.book.model.TStore;
import com.dragon.book.model.TType;

public class Book extends TBook {
    private TType tType;

    private TStore tStore;

    @Override
    public String toString() {
        return "Book{" +
                "tType=" + tType +
                ", tStore=" + tStore +
                '}';
    }

    public TType gettType() {
        return tType;
    }

    public void settType(TType tType) {
        this.tType = tType;
    }

    public TStore gettStore() {
        return tStore;
    }

    public void settStore(TStore tStore) {
        this.tStore = tStore;
    }
}
