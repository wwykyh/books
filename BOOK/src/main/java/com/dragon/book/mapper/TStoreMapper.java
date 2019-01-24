package com.dragon.book.mapper;

import com.dragon.book.model.TStore;
import com.dragon.book.model.TStoreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TStoreMapper {
    int countByExample(TStoreExample example);

    int deleteByExample(TStoreExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TStore record);

    int insertSelective(TStore record);

    List<TStore> selectByExample(TStoreExample example);

    TStore selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TStore record, @Param("example") TStoreExample example);

    int updateByExample(@Param("record") TStore record, @Param("example") TStoreExample example);

    int updateByPrimaryKeySelective(TStore record);

    int updateByPrimaryKey(TStore record);
}