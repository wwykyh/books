package com.dragon.book.controller;

import com.dragon.book.service.my.impl.ModifyPasswordServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 我的模块：修改密码Conrtoller层
 */
@Controller
@RequestMapping("/modifypassword")
public class ModifyPasswordController {

    @Autowired
    private ModifyPasswordServiceImpl modifyPasswordServiceImpl;

    /**
     * 跳转到修改密码界面
     * @return
     * zzm
     */
    @RequestMapping("/tomodifypassword")
    public String modifyPasswordIndex(){
        return "/my/modifyPassword";
    }

    /**
     * 密码修改
     * @param oldpwd 旧密码
     * @param newpwd 新密码
     * @param userid 用户ID
     * @return
     */
    @RequestMapping("/updatapassword")
    @ResponseBody
    public String upDataPassword(@Param("oldpwd")String oldpwd,@Param("newpwd")String newpwd,@Param("userid")int userid) {
        String message;
        if (null != oldpwd && null != newpwd &&  userid!=0&& "" != oldpwd&& "" != newpwd) {
            message = modifyPasswordServiceImpl.modifyPassword(oldpwd, userid, newpwd);
            return message;
        } else {
            message = "用户ID与新密码或旧密码不能为空！";
            return message;
        }
    }
}
