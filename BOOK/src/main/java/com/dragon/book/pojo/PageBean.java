package com.dragon.book.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassNamepageResulte
 * @Description TODO
 * @Author liulei
 * @Date 2019/1/28
 */
public class PageBean implements Serializable {
    private Integer page ;
    private Integer pagesize ;
    private List rows ;
    private Integer total ;

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
