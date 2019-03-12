package com.dragon.book.mapper.my;

import org.apache.ibatis.annotations.Param;

/**
 * 我的模块：修改密码Dao层
 * zzm
 */
public interface ModifyPasswordDao {

    /**
     * 更新密码
     * @param userid
     * @param pwd
     */
    void upDataPwd(@Param("userid")int userid,@Param("pwd")String pwd);

    /**
     * 查询密码
     * @param userid
     * @return
     */
    String selectolddpwd(int userid);

}
