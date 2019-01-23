package com.dragon.book.mapper;

import com.dragon.book.model.TBorrow;
import com.dragon.book.model.TBorrowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TBorrowMapper {
    int countByExample(TBorrowExample example);

    int deleteByExample(TBorrowExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TBorrow record);

    int insertSelective(TBorrow record);

    List<TBorrow> selectByExampleWithBLOBs(TBorrowExample example);

    List<TBorrow> selectByExample(TBorrowExample example);

    TBorrow selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TBorrow record, @Param("example") TBorrowExample example);

    int updateByExampleWithBLOBs(@Param("record") TBorrow record, @Param("example") TBorrowExample example);

    int updateByExample(@Param("record") TBorrow record, @Param("example") TBorrowExample example);

    int updateByPrimaryKeySelective(TBorrow record);

    int updateByPrimaryKeyWithBLOBs(TBorrow record);

    int updateByPrimaryKey(TBorrow record);
}