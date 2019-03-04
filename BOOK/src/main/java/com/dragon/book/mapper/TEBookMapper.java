package com.dragon.book.mapper;

import com.dragon.book.model.TEBook;
import com.dragon.book.model.TEBookExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TEBookMapper {
    int countByExample(TEBookExample example);

    int deleteByExample(TEBookExample example);

    int deleteByPrimaryKey(String eBookId);

    int insert(TEBook record);

    int insertSelective(TEBook record);

    List<TEBook> selectByExample(TEBookExample example);

    TEBook selectByPrimaryKey(String eBookId);

    int updateByExampleSelective(@Param("record") TEBook record, @Param("example") TEBookExample example);

    int updateByExample(@Param("record") TEBook record, @Param("example") TEBookExample example);

    int updateByPrimaryKeySelective(TEBook record);

    int updateByPrimaryKey(TEBook record);
}