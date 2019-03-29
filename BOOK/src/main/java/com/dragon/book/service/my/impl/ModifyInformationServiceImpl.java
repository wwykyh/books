package com.dragon.book.service.my.impl;
/**
 * 我的模块：修改信息service层
 *zzm
 */

import com.dragon.book.mapper.my.ModifyInformationDao;
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
        modifyInformationDao.upDataInformation(xm,lxfs,dz,bm,grsm,userId);
        }
        message = "用户ID不能为空！";
        return message;
    }
}
