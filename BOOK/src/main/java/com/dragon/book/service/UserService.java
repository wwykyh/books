package com.dragon.book.service;

import com.dragon.book.model.TCompensate;
import com.dragon.book.model.TSysUser;
import com.dragon.book.pojo.QueryVo;
import com.dragon.book.util.PageBean;
import com.github.pagehelper.Page;

public interface UserService {

    /**
     * 获取登录用户信息
     *
     * @param username 用户名
     * @param pwd      密码
     * @return
     */
    public TSysUser getUser(String username, String pwd);

    /**
     * 检查用户名是否被注册
     *
     * @param username 用户名
     * @return
     */
    public TSysUser checkUsername(String username);

    /**
     * @param username 用户名
     * @param pwd      密码
     * @param email    邮箱
     * @return
     */
    public int regUser(String username, String pwd, String email);

    public TSysUser getUserInfo(int userId);

    /**
     * 进行密码加密
     * * @param pwd 密码
     * * @return
     */
    public String encryption(String pwd);

    public boolean updataByUser(TSysUser user);

    
    /**
     * 用户管理
     * @param name 用户名
     * @return
     */
    public TSysUser getUserInfo(String name);

    public boolean deleteUser(int userId);

    public boolean updataUser(TSysUser user);

    public PageBean getAllUserByPage(PageBean pageBean, QueryVo vo);

    public boolean deleteBlackUser(int userId);

    public PageBean getBlackListByPage(PageBean pageBean, QueryVo vo);

    public PageBean getPcByPage(PageBean pageBean,QueryVo vo);

    public boolean updataPc(TCompensate compensate);

    public boolean addPc(TCompensate compensate);

    public boolean delPc(int id);
}
