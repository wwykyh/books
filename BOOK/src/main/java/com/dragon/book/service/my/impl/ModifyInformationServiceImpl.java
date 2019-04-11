package com.dragon.book.service.my.impl;
/**
 * 我的模块：修改信息service层
 *zzm
 */

import com.dragon.book.mapper.my.ModifyInformationDao;
import com.dragon.book.model.TSysUser;
import com.dragon.book.service.my.IModifyInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModifyInformationServiceImpl implements IModifyInformationService {

    @Autowired
    private ModifyInformationDao modifyInformationDao;

    /**
     * 修改信息Servicec层方法
     * @param xm
     * @param lxfs
     * @param dz
     * @param bm
     * @param grsm
     * @param userId
     */
    public String modifyInformation(String xm,String lxfs,String dz,String bm,String grsm,int userId){
        String message;
        if(0!=userId){
            if(null!=xm||null!=lxfs||null!=dz||null!=bm||null!=grsm){
                modifyInformationDao.upDataInformation(xm,lxfs,dz,bm,grsm,userId);
                return "成功";
            }
            return "修改信息不能全为空!";
        }
        message = "用户ID不能为空！";
        return message;
    }

    /**
     * 查询新的用户信息
     * @param userId
     * @return
     */
    public TSysUser findUserInfo(int userId){
    return modifyInformationDao.findUserInfo(userId);
    }
}
