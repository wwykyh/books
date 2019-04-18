package com.dragon.book.mapper;

import com.dragon.book.model.TBookAnalyze;
import com.dragon.book.model.TBorrow;
import com.dragon.book.pojo.LineChart;

import java.util.List;

/**
 * 图书分析Mapper
 */

public interface BookAnalyzeMapper {
    //查找借用的书籍的数量
    List<TBookAnalyze> selBorrowNum(TBorrow tBorrow);
    //查找十二个月借阅量
    LineChart selLineCharts(LineChart lineChart);
}
