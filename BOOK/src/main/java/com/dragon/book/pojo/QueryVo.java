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
    @Override
    public String toString() {
        return "QueryVo{" +
                "book=" + book +
                ", store=" + store +
                ", dim='" + dim + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", num=" + num +
                ", userName='" + userName + '\'' +
                '}';
    }

    private TBook book ;
    private TStore store ;
    private String dim ;
    private Integer start ;
    private Integer end ;
    private int num;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

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
