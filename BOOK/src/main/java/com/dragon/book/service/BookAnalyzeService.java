package com.dragon.book.service;

import com.dragon.book.model.TBookAnalyze;

import java.util.List;
/**
 *借阅量查询接口
 */

public interface BookAnalyzeService {
    /**
     *获取借阅量接口
     */
    List<TBookAnalyze>  getBoorowNum();
}
