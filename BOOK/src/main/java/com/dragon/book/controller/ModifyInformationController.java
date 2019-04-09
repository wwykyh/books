package com.dragon.book.controller;

/**
 * 我的模块：修改信息Conrtoller层
 * zzm
 */

import com.dragon.book.model.TSysUser;
import com.dragon.book.service.my.IModifyInformationService;
import com.dragon.book.service.my.impl.ModifyInformationServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/modifyInformation")
public class ModifyInformationController {
    @Autowired
    private IModifyInformationService modifyInformationService;

    /**
     * 跳转到修改信息界面
     * @return
     * zzm
     */
    @RequestMapping("/modifyIndex")
    public String modifyInfotmationIndex(){
        return "my/modifyInformation";
    }

    /**
     * 修改信息
     * @param xm 姓名
     * @param lxfs 联系方式
     * @param dz 地址
     * @param bm 部门
     * @param grsm 个人说明
     * @param userId 用户ID
     * @return
     */
    @RequestMapping("/doModifyInfortmation")
    @ResponseBody
    public String modifyInfotmation(@Param("xm")String xm,@Param("lxfs")String lxfs,@Param("dz")String dz,@Param("bm")String bm,@Param("grsm")String grsm,@Param("userId")int userId,HttpSession session,
                                    HttpServletRequest request){
        String message;
        if(""!=xm||""!=lxfs||""!=dz||""!=bm||""!=grsm){
            message=modifyInformationService.modifyInformation(xm,lxfs,dz,bm,grsm,userId);

            if("成功".equals(message)){
                message = "修改成功！";
                TSysUser tSysUser = modifyInformationService.findUserInfo(userId);

                if(null!=tSysUser){
                    session.setAttribute("user", tSysUser);
                    session.setMaxInactiveInterval(30 * 60);}
                return message;
            }
        }else {
            message = "修改失败！";
            return message;
        }

        message = "修改失败！";
        return message;
    }

}
