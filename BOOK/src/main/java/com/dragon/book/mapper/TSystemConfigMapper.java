package com.dragon.book.mapper;

import com.dragon.book.model.TSystemConfig;
import com.dragon.book.model.TSystemConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSystemConfigMapper {
    int countByExample(TSystemConfigExample example);

    int deleteByExample(TSystemConfigExample example);

    int insert(TSystemConfig record);

    int insertSelective(TSystemConfig record);

    List<TSystemConfig> selectByExample(TSystemConfigExample example);

    int updateByExampleSelective(@Param("record") TSystemConfig record, @Param("example") TSystemConfigExample example);

    int updateByExample(@Param("record") TSystemConfig record, @Param("example") TSystemConfigExample example);
}