package com.dragon.book.mapper;

import com.dragon.book.model.TUserBook;
import com.dragon.book.model.TUserBookExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUserBookMapper {
    int countByExample(TUserBookExample example);

    int deleteByExample(TUserBookExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TUserBook record);

    int insertSelective(TUserBook record);

    List<TUserBook> selectByExample(TUserBookExample example);

    TUserBook selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TUserBook record, @Param("example") TUserBookExample example);

    int updateByExample(@Param("record") TUserBook record, @Param("example") TUserBookExample example);

    int updateByPrimaryKeySelective(TUserBook record);

    int updateByPrimaryKey(TUserBook record);
}