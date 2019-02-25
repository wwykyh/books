package com.dragon.book.model;

/**
 * 用来返回数据到图书归还审核界面，提供图书损耗程度选项
 */
public class TBorrowVo extends TBorrow {
    private Integer sh;  // 图书损耗程度

    public Integer getSh() {
        return sh;
    }

    public void setSh(Integer sh) {
        this.sh = sh;
    }
}