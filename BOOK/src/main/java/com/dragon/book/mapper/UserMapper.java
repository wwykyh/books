package com.dragon.book.mapper;

import com.dragon.book.model.TSysUser;
import com.dragon.book.pojo.QueryVo;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    List<TSysUser> selectAllUserByPage(QueryVo vo);

    TSysUser selectOneUserByName(String name);

    TSysUser selectOneUserById(Integer userId);

    int deleteUser(Integer userId);

    int updataUser(TSysUser user);

    int selectByDimTotal(QueryVo vo);
//    把用户从黑名单中移除
    int deleteBlackUser(Integer id);

    List<TSysUser> selectBlackListByPage(QueryVo vo);

    int selectBlackListTotalByDim(QueryVo vo);

    int getCounts(Map filter);

    List<TSysUser> selectAllUserByPage2(Map filter);
}
