package com.dragon.book.mapper;

import com.dragon.book.model.TEBookVo;

import java.util.List;
import java.util.Map;

/**
 * 中间的接口，用来获取数据到前台
 */
public interface EbookInfoMapper {

    List<TEBookVo> queryTEBookVo(Map filter);
    Integer countTEBookVo(Map filter);
}
