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
@RequestMapping("/modifyPassword")
public class ModifyPasswordController {

    @Autowired
    private ModifyPasswordServiceImpl modifyPasswordServiceImpl;

    /**
     * 跳转到修改密码界面
     * @return
     * zzm
     */
    @RequestMapping("/toModifyPassword")
    public String modifyPasswordIndex(){
        return "/my/modifyPassword";
    }

    /**
     * 密码修改
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @param userId 用户ID
     * @return
     */
    @RequestMapping("/updataPassword")
    @ResponseBody
    public String upDataPassword(@Param("oldPwd")String oldPwd,@Param("newPwd")String newPwd,@Param("userId")int userId) {
        String message;
        if (null != oldPwd && null != newPwd && 0!=userId&& "" != oldPwd&& "" != newPwd) {
            message = modifyPasswordServiceImpl.modifyPassword(oldPwd, userId, newPwd);
            return message;
        } else {
            message = "用户ID与新密码或旧密码不能为空！";
            return message;
        }
    }
}
