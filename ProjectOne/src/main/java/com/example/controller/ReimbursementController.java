package com.example.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.DBConnection;
import com.example.dao.MyUserDaoImpl;
import com.example.dao.ReimbursementDaoImpl;
import com.example.model.MyUser;
import com.example.model.Reimbursement;
import com.example.service.MyUserService;
import com.example.service.ReimbursementService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.log4j.Logger;

public class ReimbursementController {

	static DBConnection con = new DBConnection();
	static ReimbursementDaoImpl rDao = new ReimbursementDaoImpl(con);
	static ReimbursementService rServ = new ReimbursementService(rDao);
	
	private static final Logger log = Logger.getLogger(ReimbursementController.class);
	
	//new request
	public static String newReimbursement(HttpServletRequest req) throws JsonProcessingException, IOException{
		
		System.out.println("In new reimbursement controller logic");	
		MyUser myU = (MyUser)req.getSession().getAttribute("currentUser");
		
		int amount = Integer.parseInt(req.getParameter("reqAmount"));
		String description = req.getParameter("reqDescription");
		int author = myU.getUserID();
		int type = Integer.parseInt(req.getParameter("reqType"));
		
		rDao.createReimbursmentRequest(amount, description, author, type);
		
		System.out.println("In newReimb controller");
	
		
		//Reimbursement myRe;
		
		return "html/home.html";
		
	}
	
	//get requests from this user
	public static void getUsersReimbReqs(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
		MyUser myU = (MyUser)req.getSession().getAttribute("currentUser");
		List<Reimbursement> rList = rDao.getReimbursmentsByAuthorID(myU.getUserID());
		
		res.getWriter().write(new ObjectMapper().writeValueAsString(rList));
	}
	
	//get all requests
	public static void getAllReimbReqs(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {

		List<Reimbursement> rList = rDao.getReimbursments();
		res.getWriter().write(new ObjectMapper().writeValueAsString(rList));
	}
	
	//approve request
	public static String approveReimbursement(HttpServletRequest req) throws JsonProcessingException, IOException{
		
		System.out.println("In approve reimbursement controller logic");	
		MyUser myU = (MyUser)req.getSession().getAttribute("currentUser");
		
		System.out.println(req.getParameter("myReimbID"));

		int myID = Integer.parseInt(req.getParameter("myReimbID"));
		
		rDao.approveReimbursmentRequest(myU.getUserID(), myID);
		
		return "html/adminhome.html";
		
	}
	
	//deny request
	public static String denyReimbursement(HttpServletRequest req) throws JsonProcessingException, IOException{
		
		System.out.println("In deny reimbursement controller logic");	
		MyUser myU = (MyUser)req.getSession().getAttribute("currentUser");
		
		System.out.println(req.getParameter("myReimbID"));
		
		int myID = Integer.parseInt(req.getParameter("myReimbID"));
		
		rDao.denyReimbursmentRequest(myU.getUserID(), myID);
		
		return "html/adminhome.html";
		
	}
	
	
}