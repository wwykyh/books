package com.dragon.book.mapper;

import com.dragon.book.model.TSysUser;
import com.dragon.book.model.TSysUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSysUserMapper {
    int countByExample(TSysUserExample example);

    int deleteByExample(TSysUserExample example);

    int insert(TSysUser record);

    int insertSelective(TSysUser record);

    List<TSysUser> selectByExampleWithBLOBs(TSysUserExample example);

    List<TSysUser> selectByExample(TSysUserExample example);

    int updateByExampleSelective(@Param("record") TSysUser record, @Param("example") TSysUserExample example);

    int updateByExampleWithBLOBs(@Param("record") TSysUser record, @Param("example") TSysUserExample example);

    int updateByExample(@Param("record") TSysUser record, @Param("example") TSysUserExample example);
    
    TSysUser selectByPrimaryKey(Integer userId);

    int deleteByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(TSysUser record);

    int updateByPrimaryKeyWithBLOBs(TSysUser record);

    int updateByPrimaryKey(TSysUser record);
    
}