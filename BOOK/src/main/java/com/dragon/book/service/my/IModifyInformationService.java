package com.dragon.book.service.my;

import com.dragon.book.model.TSysUser;

public interface IModifyInformationService {

    /**
     * 修改用户信息
     * @param xm
     * @param lxfs
     * @param dz
     * @param bm
     * @param grsm
     * @param userId
     * @return
     */
    public String modifyInformation(String xm,String lxfs,String dz,String bm,String grsm,int userId);

    /**
     * 重新设置session信息
     * @param userId
     * @return
     */
    public TSysUser findUserInfo(int userId);

}
