package com.coderli.service;

import java.util.List;

import com.coderli.entity.User;

public interface UserService {
	//�����ݿ����һ���û���Ϣ
	int addUserInfoService(User user);
	//��ȡ���е��û�����
	List<User> getAllUserInfoService();
	//�����û���id��ȡ�û���Ϣ
	User getUserInfoByUidService(String uid);

}
