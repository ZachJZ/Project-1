package com.example.service;

import com.example.dao.DBConnection;
import com.example.dao.ReimbursementDaoImpl;

public class ReimbursementService {

	private DBConnection DBCon;
	private ReimbursementDaoImpl rDao;
	
	public ReimbursementService() {
		// TODO Auto-generated constructor stub
	}
	
	public ReimbursementService(ReimbursementDaoImpl rDao) {
		super();
		this.rDao = rDao;
	}
	
	public ReimbursementService(DBConnection DBCon) {
		this.DBCon = DBCon;
				
	}
	
}
