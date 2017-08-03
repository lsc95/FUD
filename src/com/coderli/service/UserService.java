package com.coderli.service;

import java.util.List;

import com.coderli.entity.User;

public interface UserService {
	//向数据库插入一条用户信息
	int addUserInfoService(User user);
	//获取所有的用户数据
	List<User> getAllUserInfoService();
	//根据用户的id获取用户信息
	User getUserInfoByUidService(String uid);

}
