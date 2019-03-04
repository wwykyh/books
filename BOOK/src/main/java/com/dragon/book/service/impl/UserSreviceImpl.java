package com.dragon.book.service.impl;

import java.util.List;

import com.dragon.book.util.Caesar;
import com.dragon.book.util.DataOperator;
import com.dragon.book.util.PasswordAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dragon.book.mapper.TSysUserMapper;
import com.dragon.book.model.TSysUser;
import com.dragon.book.model.TSysUserExample;
import com.dragon.book.model.TSysUserExample.Criteria;
import com.dragon.book.service.UserService;

@Service
public class UserSreviceImpl implements UserService {

	@Autowired
	private TSysUserMapper userMapper;

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

    public String encryption(String pwd){
        DataOperator data;
        String pass;
        Caesar caesar = new Caesar();
        data = new PasswordAdapter( caesar);
        pass = data.doEncrypt(pwd);
        data.setPassword(pass);
        return data.getPassword();
    }
	public TSysUserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(TSysUserMapper userMapper) {
		this.userMapper = userMapper;
	}
}
