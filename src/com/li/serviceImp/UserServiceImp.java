package com.li.serviceImp;

import java.sql.SQLException;

import com.li.dao.UserDao;
import com.li.entity.User;
import com.li.service.UserService;

public class UserServiceImp implements UserService{

	UserDao userdao = new UserDao();
	public void add(User user) throws SQLException {
		userdao.add(user);
	}
	public Boolean doLogin(User loginUser) throws SQLException {
		return userdao.doLogin(loginUser);
	}
	public User isExist(String username) throws SQLException {
		User loginUser = userdao.isExist(username);
		return loginUser;
	}
}
