package com.dragon.book.mapper;

import com.dragon.book.model.TPublish;
import com.dragon.book.model.TPublishExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TPublishMapper {
    int countByExample(TPublishExample example);

    int deleteByExample(TPublishExample example);

    int deleteByPrimaryKey(Integer pubId);

    int insert(TPublish record);

    int insertSelective(TPublish record);

    List<TPublish> selectByExample(TPublishExample example);

    TPublish selectByPrimaryKey(Integer pubId);

    int updateByExampleSelective(@Param("record") TPublish record, @Param("example") TPublishExample example);

    int updateByExample(@Param("record") TPublish record, @Param("example") TPublishExample example);

    int updateByPrimaryKeySelective(TPublish record);

    int updateByPrimaryKey(TPublish record);
}