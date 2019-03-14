package com.dragon.book.mapper;

import java.util.List;
import com.dragon.book.model.TEbookType;
import com.dragon.book.model.TEbookTypeExample;
import org.apache.ibatis.annotations.Param;

public interface TEbookTypeMapper {
    int countByExample(TEbookTypeExample example);

    int deleteByExample(TEbookTypeExample example);

    int deleteByPrimaryKey(Integer ebookTypeid);

    int insert(TEbookType record);

    int insertSelective(TEbookType record);

    List<TEbookType> selectByExample(TEbookTypeExample example);

    TEbookType selectByPrimaryKey(Integer ebookTypeid);

    int updateByExampleSelective(@Param("record") TEbookType record, @Param("example") TEbookTypeExample example);

    int updateByExample(@Param("record") TEbookType record, @Param("example") TEbookTypeExample example);

    int updateByPrimaryKeySelective(TEbookType record);

    int updateByPrimaryKey(TEbookType record);
}