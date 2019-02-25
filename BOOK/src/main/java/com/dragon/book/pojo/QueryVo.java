package com.dragon.book.pojo;

import com.dragon.book.model.TBook;
import com.dragon.book.model.TStore;

import java.io.Serializable;

/**
 * @ClassNameQueryVo
 * @Description TODO
 * @Author liulei
 * @Date 2019/1/28
 */
public class QueryVo implements Serializable {
    private TBook book ;
    private TStore store ;
    private String dim ;
    private Integer start ;
    private Integer end ;

    public TBook getBook() {
        return book;
    }

    public void setBook(TBook book) {
        this.book = book;
    }

    public TStore getStore() {
        return store;
    }

    public void setStore(TStore store) {
        this.store = store;
    }

    public String getDim() {
        return dim;
    }

    public void setDim(String dim) {
        this.dim = dim;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }
}
