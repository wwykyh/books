package com.dragon.book.controller;

import com.dragon.book.service.my.ModifyPasswordService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 我的模块：修改密码Conrtoller层
 */
@Controller
@RequestMapping("/modifypassword")
public class ModifyPasswordController {

    @Autowired
    private ModifyPasswordService modifyPasswordService;

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
     * 修改密码
     * @return
     */
    @RequestMapping("/updatapassword")
    @ResponseBody
    public String upDataPassword(@Param("oldpwd")String oldpwd,@Param("newpwd")String newpwd,@Param("userid")int userid){
        if(newpwd!=null){
            int flag;
            flag =  modifyPasswordService.modifyPassword(oldpwd,userid,newpwd);
            if(flag==1){
            return "success";
            }else {
                return  "error";
            }
        }else {
        return  "error";}
    }
}
