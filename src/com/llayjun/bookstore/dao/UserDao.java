package com.llayjun.bookstore.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.llayjun.bookstore.domain.Users;
import com.llayjun.bookstore.utils.C3P0Util;

public class UserDao {

	public void addUser(Users _users) {
		// TODO Auto-generated method stub
		QueryRunner qRunner = new QueryRunner(C3P0Util.getDataSource());
		String _sql = "insert into user(username,PASSWORD,gender,email,telephone,introduce,activecode,state,registTime) values (?,?,?,?,?,?,?,?,?);";
		try {
			qRunner.update(_sql,_users.getUsername(),_users.getPassword(),_users.getGender(),_users.getEmail(),_users.getTelephone(),_users.getIntroduce(),_users.getActiveCode(),_users.getState(),_users.getRegistTime());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
