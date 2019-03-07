package com.dragon.book.service.my;
/**
 * 我的模块：修改信息service层
 *zzm
 */

import com.dragon.book.mapper.my.ModifyInformationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModifyInformationService {

    @Autowired
    private ModifyInformationDao modifyInformationDao;

    public void modifyInformation(String xm,String lxfs,String dz,String bm,String grsm,int userid){
        modifyInformationDao.upDataInformation(xm,lxfs,dz,bm,grsm,userid);
    }
}
