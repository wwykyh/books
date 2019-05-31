package com.dragon.book.service;

import com.dragon.book.model.TBookAnalyze;
import com.dragon.book.model.TBorrow;
import com.dragon.book.pojo.LineChart;

import java.util.List;
/**
 *借阅量查询接口
 */

public interface BookAnalyzeService {
    /**
     *获取借阅量接口
     */
    List<TBookAnalyze>  getBoorowNum(String month);
    /**
     * 获取十二个月借阅量
     */
    List<LineChart> getLineChart();
}
