package com.coderli.dao;

import java.util.List;

import com.coderli.entity.User;

public interface UserDao {
	//�����ݿ����һ���û���Ϣ
	int addUserInfo(User user);
	//��ȡ���е��û���Ϣ
	List<User> getAllUserInfo();
	//�����û���id��ȡ�û���Ϣ
	User getUserInfoByUid(String uid);

}
