package com.coderli.service.imp;

import java.util.List;

import com.coderli.dao.UserDao;
import com.coderli.dao.imp.UserDaoImp;
import com.coderli.entity.User;
import com.coderli.service.UserService;

public class UserServiceImp implements UserService{
	private UserDao dao = new UserDaoImp();
	//向数据库插入一条用户信息
	@Override
	public int addUserInfoService(User user) {
		return dao.addUserInfo(user);
	}
	//获取所有的用户信息
	@Override
	public List<User> getAllUserInfoService() {
		return dao.getAllUserInfo();
	}
	//根据用户的id获取用户数据
	@Override
	public User getUserInfoByUidService(String uid) {
		return dao.getUserInfoByUid(uid);
	}

}
