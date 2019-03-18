package com.dragon.book.service.impl;

import com.dragon.book.mapper.TSysUserMapper;
import com.dragon.book.mapper.UserMapper;
import com.dragon.book.model.TCompensate;
import com.dragon.book.model.TSysUser;
import com.dragon.book.model.TSysUserExample;
import com.dragon.book.model.TSysUserExample.Criteria;
import com.dragon.book.pojo.HistoryInfo;
import com.dragon.book.pojo.PcInfo;
import com.dragon.book.pojo.QueryVo;
import com.dragon.book.service.UserService;
import com.dragon.book.util.Caesar;
import com.dragon.book.util.DataOperator;
import com.dragon.book.util.PageBean;
import com.dragon.book.util.PasswordAdapter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserSreviceImpl implements UserService {

    @Autowired
    private TSysUserMapper userMapper;

    @Autowired
    private UserMapper userMapperWn;

    @Override
    public TSysUser getUser(String username, String pwd) {
        // TODO Auto-generated method stub
        TSysUserExample example = new TSysUserExample();
        Criteria criteria = example.createCriteria();
        criteria.andXmEqualTo(username);
        criteria.andPwdEqualTo(pwd);
        List<TSysUser> list = userMapper.selectByExample(example);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public int regUser(String username, String pwd, String email) {
        // TODO Auto-generated method stub
        TSysUser user = new TSysUser();
        user.setEmail(email);
        user.setXm(username);
        user.setPwd(pwd);
        return userMapper.insert(user);
    }

    @Override
    public TSysUser checkUsername(String username) {
        // TODO Auto-generated method stub
        TSysUserExample example = new TSysUserExample();
        Criteria criteria = example.createCriteria();
        criteria.andXmEqualTo(username);
        List<TSysUser> list = userMapper.selectByExample(example);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public TSysUser getUserInfo(int userId) {
        // TODO Auto-generated method stub
        TSysUser user = userMapper.selectByPrimaryKey(userId);
        System.out.println(user.toString());
        return user;
    }

    @Override
    public String encryption(String pwd) {
        DataOperator data;
        String pass;
        Caesar caesar = new Caesar();
        data = new PasswordAdapter(caesar);
        pass = data.doEncrypt(pwd);
        data.setPassword(pass);
        return data.getPassword();
    }

    @Override
    public boolean updataByUser(TSysUser user) {

        int i = userMapper.updateByPrimaryKeySelective(user);

        return i>0 ? true:false;
    }

    public TSysUserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(TSysUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    //加入的按页查找信息
    @Override
    public PageBean getAllUserByPage(PageBean pageBean, QueryVo vo) {
        if (vo.getDim() == null || vo.getDim() ==""){
            vo.setDim(null);
        }
        List<TSysUser> users = userMapperWn.selectAllUserByPage(vo);
        int Total =userMapperWn.selectByDimTotal(vo);
        pageBean.setTotal(Total);
        pageBean.setRows(users);
        return pageBean;
    }


    @Override
    public boolean deleteBlackUser(int userId) {
        int i = userMapperWn.deleteBlackUser(userId);
        return i>0 ? true:false;
    }

    @Override
    public PageBean getBlackListByPage(PageBean pageBean, QueryVo vo) {
        if (vo.getDim() == null || vo.getDim() ==""){
            vo.setDim(null);
        }
        List<TSysUser> users = userMapperWn.selectBlackListByPage(vo);
        int Total =userMapperWn.selectBlackListTotalByDim(vo);
        pageBean.setTotal(Total);
        pageBean.setRows(users);
        return pageBean;
    }

    @Override
    public TSysUser getUserInfo(String name) {
        return null;
    }

    @Override
    public boolean deleteUser(int userId) {
        int i = userMapperWn.deleteUser(userId);
        return i>0 ? true:false;
    }

    @Override
    public boolean updataUser(TSysUser user) {
        int row = userMapperWn.updataUser(user);
        return row > 0 ? true : false ;
    }

    @Override
    public PageBean getPcByPage(PageBean pageBean, QueryVo vo) {
        return null;
    }

    @Override
    public boolean updataPc(TCompensate compensate) {
        return false;
    }

    @Override
    public boolean addPc(TCompensate compensate) {
        return false;
    }

    @Override
    public boolean delPc(int id) {
        return false;
    }

    @Override
    public Integer getCounts(Map<String, Object> searchParams) {
        return userMapperWn.getCounts(searchParams);
    }

    @Override
    public List<TSysUser> getAllUserByPage(Map<String, Object> searchParams) {
        return userMapperWn.selectAllUserByPage2(searchParams);
    }

    @Override
    public Integer getPcCounts() {
        return userMapperWn.getPcCounts();
    }

    @Override
    public PageBean gestAllPcInfoByPage(PageBean pageBean) {
        PageHelper.startPage(pageBean.getPage(),pageBean.getPagesize()) ;
        List<PcInfo> list =  userMapperWn.selectAllPcInfoByPage(pageBean);
        PageInfo<PcInfo> info = new PageInfo<>(list) ;
        pageBean.setTotal(userMapperWn.getPcCounts());
        pageBean.setRows(info.getList());
        return pageBean;
    }

    @Override
    public boolean deletePcById(int pcId) {
        int status = userMapperWn.deletePcById(pcId);
        return status>0?true:false;
    }

    @Override
    public PcInfo selectPcById(int id) {
        PcInfo pcInfo = userMapperWn.selectPcById(id);
        return pcInfo;
    }

    @Override
    public boolean updataByPc(TCompensate tCompensate) {
        int i = userMapperWn.updatePc(tCompensate);
        return i>0?true:false;
    }
}
