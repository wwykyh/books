package com.dragon.book.mapper.my;

import org.apache.ibatis.annotations.Param;

/**
 * 我的模块：修改信息Dao层
 * zzm
 */
public interface ModifyInformationDao {

    public void upDataInformation(@Param("xm")String xm, @Param("lxfs")String lxfs, @Param("dz")String dz, @Param("bm")String bm, @Param("grsm")String grsm, @Param("userid")int userid);

}
