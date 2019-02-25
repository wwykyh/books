package com.dragon.book.utils;

import java.io.Serializable;
import java.util.List;

/**
 * 分页bean
 */
public class PageBean implements Serializable {
    private Integer page;
    private Integer pagesize;
    private List rows;      // 用户存储行数据
    private Integer total;   // 记录总数

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
