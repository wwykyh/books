package com.dragon.book.mapper.my;

import com.dragon.book.model.TSysUser;
import org.apache.ibatis.annotations.Param;

/**
 * 我的模块：修改信息Dao层
 * zzm
 */
public interface ModifyInformationDao {

    /**
     * 修改信息接口
     *
     * @param xm
     * @param lxfs
     * @param dz
     * @param bm
     * @param grsm
     * @param userId
     */
    public void upDataInformation(@Param("xm") String xm, @Param("lxfs") String lxfs, @Param("dz") String dz, @Param("bm") String bm, @Param("grsm") String grsm, @Param("userId") int userId);

    /**
     * 查询新的用户信息
     *
     * @param userId
     * @return
     */
    public TSysUser findUserInfo(@Param("userId") int userId);

}
