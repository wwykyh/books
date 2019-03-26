package com.dragon.book.mapper;

import com.dragon.book.model.TBookAnalyze;
import java.util.List;

/**
 * 图书分析Mapper
 */

public interface BookAnalyzeMapper {
    //查找借用的书籍的数量
    List<TBookAnalyze> selBorrowNum();
}
