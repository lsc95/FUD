package com.coderli.dao.imp;

import java.util.List;

import com.coderli.dao.UserDao;
import com.coderli.entity.User;
import com.coderli.utils.DBUtils;

public class UserDaoImp extends BaseDaoImp implements UserDao{
	//添加用户信息
	@Override
	public int addUserInfo(User user) {
		String sql="insert into p_user values(default,?,?,?,?,?)";
		return DBUtils.executeDML(sql, user.getUname(),user.getPwd(),user.getRealName(),user.getPhotoName(),user.getType());
	}
	//查询所有的用户信息
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUserInfo() {
		String sql="select * from p_user";
		return (List<User>) queryAll(sql, User.class, null);
	}
	//根据用户id获取用户数据
	@Override
	public User getUserInfoByUid(String uid) {
		String sql="select * from p_user where uid=?";
		return (User) quertyOneRow(sql, User.class, new Object[]{uid});
	}

}
