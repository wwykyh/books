package com.dragon.book.service;

import com.dragon.book.model.TCompensate;
import com.dragon.book.model.TSysUser;
import com.dragon.book.model.TSystemConfig;
import com.dragon.book.model.TType;
import com.dragon.book.pojo.BlackList;
import com.dragon.book.pojo.PcInfo;
import com.dragon.book.pojo.QueryVo;
import com.dragon.book.util.PageBean;

import java.util.List;
import java.util.Map;

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

//修改用户是否黑名单字段
    public boolean deleteBlackUser(int userId);

    public  List<BlackList> getBlackListByPage(Map<String, Object> searchParams);

    public PageBean getPcByPage(PageBean pageBean, QueryVo vo);

    public boolean updataPc(TCompensate compensate);

    public boolean addPc(TCompensate compensate);

    public boolean delPc(int id);

    Integer getCounts(Map<String, Object> searchParams);

    List<TSysUser> getAllUserByPage(Map<String, Object> searchParams);

    boolean updataByUser(TSysUser user);

    Integer getPcCounts();

    PageBean gestAllPcInfoByPage(PageBean pageBean);

    boolean deletePcById(int pcId);

    PcInfo selectPcById(int pcId);

    boolean updataByPc(TCompensate tCompensate);

    TSystemConfig getConfic();

    Integer getBlCounts(Map<String, Object> searchParams);
    //从黑名单移除用户
    boolean deleteBlUser(int userId);
    //定时器检测黑名单释放人员
    void releaseUser();

    //通过主键查询type信息
    TType selectTypeById(int Id);

    //重置密码
    int updataUserPwd(int userId);

    public int managerDoReg(TSysUser tSysUser);
}
