package com.coderli.service.imp;

import java.util.List;

import com.coderli.dao.UserDao;
import com.coderli.dao.imp.UserDaoImp;
import com.coderli.entity.User;
import com.coderli.service.UserService;

public class UserServiceImp implements UserService{
	private UserDao dao = new UserDaoImp();
	//�����ݿ����һ���û���Ϣ
	@Override
	public int addUserInfoService(User user) {
		return dao.addUserInfo(user);
	}
	//��ȡ���е��û���Ϣ
	@Override
	public List<User> getAllUserInfoService() {
		return dao.getAllUserInfo();
	}
	//�����û���id��ȡ�û�����
	@Override
	public User getUserInfoByUidService(String uid) {
		return dao.getUserInfoByUid(uid);
	}

}
