package com.dragon.book.service;

import com.dragon.book.model.TSysUser;
import com.dragon.book.pojo.QueryVo;
import com.dragon.book.util.PageBean;

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
}
