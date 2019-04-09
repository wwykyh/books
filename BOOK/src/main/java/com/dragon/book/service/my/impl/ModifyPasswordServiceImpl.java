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
     * @param oldPwd
     * @param userId
     * @param pwd
     * @return
     */
    public String modifyPassword(String oldPwd, int userId, String pwd){

        String old;
        boolean flag = isNumeric(pwd);
        int pwdLength = pwd.length();

         if(pwdLength>=6&&!flag) {
             old = modifyPasswordDao.selectOldPwd(userId);
             if (old.equals(oldPwd)) {
                 if (!old.equals(pwd)) {
                     modifyPasswordDao.upDataPwd(userId, pwd);
                     return "密码修改成功！";
                 }
                 return "新旧密码不能相同！";
             }
             return "旧密码输入错误！";
         }
         return "密码不能小于6位且不能都为数字！";
    }

    /**
     * 判断密码是否为纯数字false为非纯数字。true为纯数字
     * @param str
     * @return
     */
    public boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            int chr = str.charAt(i);
            if (chr < 48 || chr > 57) {
                    return false;
            }
        }
        return true;
    }

}
