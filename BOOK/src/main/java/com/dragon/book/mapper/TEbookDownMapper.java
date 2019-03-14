package com.dragon.book.mapper;

import com.dragon.book.model.TEbookDown;
import com.dragon.book.model.TEbookDownExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TEbookDownMapper {
    int countByExample(TEbookDownExample example);

    int deleteByExample(TEbookDownExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TEbookDown record);

    int insertSelective(TEbookDown record);

    List<TEbookDown> selectByExample(TEbookDownExample example);

    TEbookDown selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TEbookDown record, @Param("example") TEbookDownExample example);

    int updateByExample(@Param("record") TEbookDown record, @Param("example") TEbookDownExample example);

    int updateByPrimaryKeySelective(TEbookDown record);

    int updateByPrimaryKey(TEbookDown record);
}