package com.dragon.book.mapper;

import com.dragon.book.model.TBookNews;
import com.dragon.book.model.TBookNewsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TBookNewsMapper {
    int countByExample(TBookNewsExample example);

    int deleteByExample(TBookNewsExample example);

    int insert(TBookNews record);

    int insertSelective(TBookNews record);

    List<TBookNews> selectByExample(TBookNewsExample example);

    int updateByExampleSelective(@Param("record") TBookNews record, @Param("example") TBookNewsExample example);

    int updateByExample(@Param("record") TBookNews record, @Param("example") TBookNewsExample example);
}