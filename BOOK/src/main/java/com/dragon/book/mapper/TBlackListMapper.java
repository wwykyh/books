package com.dragon.book.mapper;

import com.dragon.book.model.TBlackList;
import com.dragon.book.model.TBlackListExample;
import java.util.List;
import java.util.Map;

import com.dragon.book.pojo.BlackList;
import org.apache.ibatis.annotations.Param;

public interface TBlackListMapper {
    int countByExample(TBlackListExample example);

    int deleteByExample(TBlackListExample example);

    int insert(TBlackList record);

    int insertSelective(TBlackList record);

    List<TBlackList> selectByExample(TBlackListExample example);

    int updateByExampleSelective(@Param("record") TBlackList record, @Param("example") TBlackListExample example);

    int updateByExample(@Param("record") TBlackList record, @Param("example") TBlackListExample example);

    //按页查询
    List<BlackList> selectBypage( Map<String, Object> searchParams);

    //从黑名单中移除
    int updataById(int id);

    int selectCount(Map<String, Object> searchParams);
    //获取当前黑名单全部信息
    List<TBlackList> selectBlackList();

    //修改用户表中是否为黑名单字段
    int removeUserBlackList(int userId);
}