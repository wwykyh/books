package com.dragon.book.mapper;

import com.dragon.book.model.TType;
import com.dragon.book.model.TTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TTypeMapper {
    int countByExample(TTypeExample example);

    int deleteByExample(TTypeExample example);

    int deleteByPrimaryKey(Integer typeId);

    int insert(TType record);

    int insertSelective(TType record);

    List<TType> selectByExample(TTypeExample example);

    TType selectByPrimaryKey(Integer typeId);

    int updateByExampleSelective(@Param("record") TType record, @Param("example") TTypeExample example);

    int updateByExample(@Param("record") TType record, @Param("example") TTypeExample example);

    int updateByPrimaryKeySelective(TType record);

    int updateByPrimaryKey(TType record);
}