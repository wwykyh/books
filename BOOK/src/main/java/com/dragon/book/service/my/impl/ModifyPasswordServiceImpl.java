package com.dragon.book.service.my.impl;

import com.dragon.book.mapper.my.ModifyPasswordDao;
import com.dragon.book.service.my.IModifyPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 我的模块：修改密码Service层
 * zzm
 */
@Service
public class ModifyPasswordServiceImpl implements IModifyPasswordService {

    @Autowired
    private ModifyPasswordDao modifyPasswordDao;

    /**
     * 修改密码Service层方法
     * @param oldpwd
     * @param userid
     * @param pwd
     * @return
     */
    public String modifyPassword(String oldpwd,int userid,String pwd){
        String flag;
        flag = modifyPasswordDao.selectOldPwd(userid);
        if(flag.equals(oldpwd)){
        modifyPasswordDao.upDataPwd(userid,pwd);
        return "密码修改成功！";
        }else {
            return "两次密码输入不一致";
        }
    }
}
