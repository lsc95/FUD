package com.coderli.dao;

import java.util.List;

import com.coderli.entity.User;

public interface UserDao {
	//向数据库插入一条用户信息
	int addUserInfo(User user);
	//获取所有的用户信息
	List<User> getAllUserInfo();
	//根据用户的id获取用户信息
	User getUserInfoByUid(String uid);

}
