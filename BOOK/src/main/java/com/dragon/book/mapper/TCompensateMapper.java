package com.dragon.book.mapper;

import com.dragon.book.model.TCompensate;
import com.dragon.book.model.TCompensateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TCompensateMapper {
    int countByExample(TCompensateExample example);

    int deleteByExample(TCompensateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TCompensate record);

    int insertSelective(TCompensate record);

    List<TCompensate> selectByExampleWithBLOBs(TCompensateExample example);

    List<TCompensate> selectByExample(TCompensateExample example);

    TCompensate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TCompensate record, @Param("example") TCompensateExample example);

    int updateByExampleWithBLOBs(@Param("record") TCompensate record, @Param("example") TCompensateExample example);

    int updateByExample(@Param("record") TCompensate record, @Param("example") TCompensateExample example);

    int updateByPrimaryKeySelective(TCompensate record);

    int updateByPrimaryKeyWithBLOBs(TCompensate record);

    int updateByPrimaryKey(TCompensate record);
}