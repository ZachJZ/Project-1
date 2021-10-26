package com.example.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.example.controller.MyUserController;
import com.example.controller.ReimbursementController;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ViewDispatcher {//Called by the ViewServlet
							//This is the filter that determines what controller to use
	
	public static String process(HttpServletRequest req) throws JsonProcessingException, IOException {
		
		switch(req.getRequestURI()) {
			
			case "/ProjectOne/login.change":
				System.out.println("login.change dispatch");
				return MyUserController.login(req);
				
			case "/ProjectOne/logout.change":
				System.out.println("logout.change dispatch");
				return MyUserController.logout(req);
				
			case "/ProjectOne/deny.change":
				System.out.println("deny.change dispatch");
				return ReimbursementController.denyReimbursement(req); //MyUserController.logout(req);
				
			case "/ProjectOne/approve.change":
				System.out.println("approve.change dispatch");
				return ReimbursementController.approveReimbursement(req); //MyUserController.logout(req);
				
//			case "/ProjectOne/getsessionuser.json":
//				System.out.println("getsessionuser.json dispatch");
//				MyUserController.login(req);
				
			case "/ProjectOne/newreimbrequest.change":
				System.out.println("new reimbursement dispatch");
				return ReimbursementController.newReimbursement(req);
			
			default:
				System.out.println("in default dispatcher");
				return MyUserController.logout(req);
		}
	}
}
