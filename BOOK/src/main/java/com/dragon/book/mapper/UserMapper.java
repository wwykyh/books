package com.dragon.book.mapper;

import com.dragon.book.model.TBlackList;
import com.dragon.book.model.TBorrow;
import com.dragon.book.pojo.Book;
import com.dragon.book.model.TCompensate;
import com.dragon.book.model.TSysUser;
import com.dragon.book.pojo.PcInfo;
import com.dragon.book.pojo.QueryVo;
import com.dragon.book.util.PageBean;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    List<TSysUser> selectAllUserByPage(QueryVo vo);

    TSysUser selectOneUserByName(String name);

    TSysUser selectOneUserById(Integer userId);

    int deleteUser(Integer userId);

    int updataUser(TSysUser user);

    int selectByDimTotal(QueryVo vo);
//    把用户从黑名单中移除
    int deleteBlackUser(Integer id);

    List<TSysUser> selectBlackListByPage(QueryVo vo);

    int selectBlackListTotalByDim(QueryVo vo);

    int getCounts(Map filter);

    List<TSysUser> selectAllUserByPage2(Map filter);

    Integer getPcCounts();

    List<PcInfo> selectAllPcInfoByPage(PageBean pageBean);

    int  deletePcById(int id);

    PcInfo selectPcById(Integer id);

    int updatePc(TCompensate tCompensate);

    //主页
    List<TBorrow> selectUserBorrow(int userid);

    int userTotal();

    int bookTotal();

    //重置密码
    int updataUserPwd(int userId);


    TSysUser selectByName(String name);
}
