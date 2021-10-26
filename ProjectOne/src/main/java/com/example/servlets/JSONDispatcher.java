package com.example.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.controller.MyUserController;
import com.example.controller.ReimbursementController;
import com.example.model.MyUser;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONDispatcher {

public static void process(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		switch(req.getRequestURI()) {

			case "/ProjectOne/getsessionuser.json":
				System.out.println("in getsessionuser.json dispatch");
				//MyUserController.getSessionUser(req, res);
				MyUserController.getSessionUser(req, res);
				break;
				
			case "/ProjectOne/getusersrequests.json":
				System.out.println("in getusersrequests.json dispatch");
				//MyUserController.getSessionUser(req, res);
				ReimbursementController.getUsersReimbReqs(req, res);
				break;
				
			case "/ProjectOne/admindisplay.json":
				System.out.println("in admindisplay.json dispatch");
				//MyUserController.getSessionUser(req, res);
				ReimbursementController.getAllReimbReqs(req, res);
				break;
			
			default:
				System.out.println("in default json dispatcher");
				res.getWriter().write(new ObjectMapper().writeValueAsString(new MyUser()));
				//return "html/unsuccessfullogin.html";
		}
	}
}
