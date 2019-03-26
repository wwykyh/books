package com.dragon.book.controller;

/**
 * 我的模块：修改信息Conrtoller层
 * zzm
 */

import com.dragon.book.service.my.impl.ModifyInformationServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/modifyinformation")
public class ModifyInformationController {
    @Autowired
    private ModifyInformationServiceImpl modifyInformationServiceImpl;

    /**
     * 跳转到修改信息界面
     * @return
     * zzm
     */
    @RequestMapping("/modifyindex")
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
     * @param userid 用户ID
     * @return
     */
    @RequestMapping("/domodifyinfotmation")
    @ResponseBody
    public String modifyInfotmation(@Param("xm")String xm,@Param("lxfs")String lxfs,@Param("dz")String dz,@Param("bm")String bm,@Param("grsm")String grsm,@Param("userid")int userid){
        String message;
        if(""!=xm||""!=lxfs||""!=dz||""!=bm||""!=grsm){
            modifyInformationServiceImpl.modifyInformation(xm,lxfs,dz,bm,grsm,userid);
            message = "修改成功！";
        return message;
        }else {
            message = "修改失败！";
            return message;
        }
    }

}
