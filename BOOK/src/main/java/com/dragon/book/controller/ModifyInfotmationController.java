package com.dragon.book.controller;

/**
 * 我的模块：修改信息Conrtoller层
 * zzm
 */

import com.dragon.book.service.my.ModifyInformationService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/modifyinformation")
public class ModifyInfotmationController {
    @Autowired
    private ModifyInformationService modifyInformationService;

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
     * @param xm
     * @param lxfs
     * @param dz
     * @param bm
     * @param grsm
     * @param userid
     * @return
     */
    @RequestMapping("/domodifyinfotmation")
    @ResponseBody
    public String modifyInfotmation(@Param("xm")String xm,@Param("lxfs")String lxfs,@Param("dz")String dz,@Param("bm")String bm,@Param("grsm")String grsm,@Param("userid")int userid){
        if(null!=xm||null!=lxfs||null!=dz||null!=bm||null!=grsm){
            modifyInformationService.modifyInformation(xm,lxfs,dz,bm,grsm,userid);
        return "success";
        }else {
            return "error";
        }
    }

}
