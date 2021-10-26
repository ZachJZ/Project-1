package com.example.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.DBConnection;
import com.example.dao.MyUserDaoImpl;
import com.example.model.MyUser;
import com.example.service.MyUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyUserController {

	static DBConnection con = new DBConnection();
	static MyUserDaoImpl uDao = new MyUserDaoImpl(con);
	static MyUserService uServ = new MyUserService(uDao);
	
	public static String login(HttpServletRequest req) {
		
		System.out.println("In User controller login");
		
		if (!req.getMethod().equals("POST")) { //prevent login if they are not using http POST
			System.out.println("not post: bad login");
			return "html/unsuccessful.html";
		}
		
		//System.out.println("Trying to verify user");
		MyUser myUser = uServ.getUserVerify(req.getParameter("myUsername"), req.getParameter("myPassword"));
		//System.out.println(req.getParameter("myUsername") + " is the username and the password is " + req.getParameter("myPassword"));
		
		if (myUser == null) {
			System.out.println("Wrong creds");
			return "wrongcreds.change"; 
		}
		//role id - 1 is employee, 2 is manager
		else if (myUser.getRoleID() == 2){
			//admin page
			System.out.println("manager login");
			req.getSession().setAttribute("currentUser", myUser);
			return "html/adminhome.html";
		}
		else if (myUser.getRoleID() == 1){
			//employee page
			System.out.println("regular login");
			req.getSession().setAttribute("currentUser", myUser);
			return "html/home.html";
		}
		return "html/unsuccessful.html";
	}
	
	public static void getSessionUser(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
		MyUser myU = (MyUser)req.getSession().getAttribute("currentUser");
		res.getWriter().write(new ObjectMapper().writeValueAsString(myU));
		
	}

	public static String logout(HttpServletRequest req) {
		req.getSession().setAttribute("currentUser", null);
		req.getSession().invalidate();
		return "html/index.html";
	}
}
