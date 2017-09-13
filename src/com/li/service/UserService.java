package com.li.service;

import java.sql.SQLException;

import com.li.entity.User;
import com.li.serviceImp.UserServiceImp;

public interface UserService {
	UserServiceImp userserviceimp = new UserServiceImp();
	public void add(User user) throws SQLException ;
	public User isExist(String username) throws SQLException;
	public Boolean doLogin(User loginUser) throws SQLException;
}
