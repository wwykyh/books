package com.dragon.book.mapper.my;

import org.apache.ibatis.annotations.Param;

/**
 * 我的模块：修改密码Dao层
 */
public interface ModifyPasswordDao {

    void upDataPwd(@Param("userid")int userid,@Param("pwd")String pwd);

    String selectOldpwd(int userid);

}
