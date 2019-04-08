package com.dragon.book.mapper;

import com.dragon.book.model.TOvertime;
import com.dragon.book.model.TOvertimeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TOvertimeMapper {
    int countByExample(TOvertimeExample example);

    int deleteByExample(TOvertimeExample example);

    int insert(TOvertime record);

    int insertSelective(TOvertime record);

    List<TOvertime> selectByExample(TOvertimeExample example);

    int updateByExampleSelective(@Param("record") TOvertime record, @Param("example") TOvertimeExample example);

    int updateByExample(@Param("record") TOvertime record, @Param("example") TOvertimeExample example);
}