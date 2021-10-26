package com.example.service;

import com.example.dao.DBConnection;
import com.example.dao.MyUserDaoImpl;
import com.example.model.MyUser;

public class MyUserService {

	private DBConnection DBCon;
	private MyUserDaoImpl uDao;
	
	public MyUserService() {
		// TODO Auto-generated constructor stub
	}
	
	public MyUserService(MyUserDaoImpl uDao) {
		super(); //idk what this does. Doesn't seem to break anything one way or the other
		this.uDao = uDao;
	}
	
	public MyUserService(DBConnection DBCon) {
		this.DBCon = DBCon;
	}
	
	public MyUser getUserVerify(String username, String password) {
		MyUser user = uDao.getUserByName(username);
		
		//System.out.println(user.getPassword());
		
		if (user != null) {
			System.out.println(user.getPassword());
			if (user.getPassword().equals(password)) {
				return user;
			}
		}
		//System.out.println(user.toString());
		return null;
	}
	
	
	
}
