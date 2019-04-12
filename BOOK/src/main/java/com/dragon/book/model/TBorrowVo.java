package com.dragon.book.model;

/**
 *
 * 用来返回数据到图书归还确认界面(bookRevertCheck.jsp)，提供图书损耗程度选项
 */
public class TBorrowVo extends TBorrow {
    private Integer sh;         // 图书损耗程度
    private String xm;          // 借阅者姓名
    private String isbn;        // 图书编号

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public Integer getSh() {
        return sh;
    }

    public void setSh(Integer sh) {
        this.sh = sh;
    }
}