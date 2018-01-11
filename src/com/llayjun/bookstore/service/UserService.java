package com.llayjun.bookstore.service;

import com.llayjun.bookstore.dao.UserDao;
import com.llayjun.bookstore.domain.Users;

public class UserService {

	private UserDao mUserDao = new UserDao();

	public void regist(Users _users) {
		mUserDao.addUser(_users);
	}

}
