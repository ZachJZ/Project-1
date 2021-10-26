package com.example.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.example.model.Reimbursement;

public class ReimbursementDaoImpl {

	//everything
	//everything with id
	//everything with status
	
	private DBConnection DBCon = new DBConnection();

	private static final Logger log = Logger.getLogger(ReimbursementDaoImpl.class);

	
	public ReimbursementDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	public ReimbursementDaoImpl(DBConnection DBCon) {
		this.DBCon = DBCon;
	}
	
	//user
	public Reimbursement createReimbursmentRequest(int amount, String description, int authorID, int type) {
		
		try(Connection con = DBCon.getDBConnection()){
			String sql = "select submit_reimburstment_request(?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, amount);
			ps.setString(2, description);
			ps.setInt(3, authorID);
			ps.setInt(4, type); //1 for business 2 for personal
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new Reimbursement(amount, description, authorID, type);
	}
	
	//user
	public List<Reimbursement> getReimbursmentsByAuthorID(int authorID){
		
		List<Reimbursement> rList = new ArrayList<>();
		
		try(Connection con = DBCon.getDBConnection()){
			String sql = "select * from ers_reimbursement where reimb_author = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, authorID);
			ps.execute();
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {	 
				
				rList.add(new Reimbursement (
						//rs.getInt(1), rs.getTimestamp(2).toLocalDateTime(), rs.getTimestamp(3).toLocalDateTime(), 
						rs.getInt(1), 
						rs.getString(4), rs.getBlob(5), rs.getInt(6), 
						rs.getInt(7), rs.getInt(8), rs.getInt(9),  
						rs.getInt(10)));				
			}
		} catch (SQLException e) {
			log.fatal("A SQLException occurred.");
		}
		System.out.println(rList.toString());
		log.info("reimb list @ getReimbursmentsByAuthorID: " + rList);

		return rList;
	}
		
	//admin
	public boolean denyReimbursmentRequest(int resolverID, int caseID) {
		
		try(Connection con = DBCon.getDBConnection()){
			String sql = "select deny_reimburstment_request(?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, resolverID);
			ps.setInt(2, caseID);
			ps.execute();
			
			} catch (SQLException e) {
				log.fatal("A SQLException occurred.");
			}
		log.info("denied request");
		return true;
	}
		
	//admin
	public boolean approveReimbursmentRequest(int resolverID, int caseID) {
		
		try(Connection con = DBCon.getDBConnection()){
			String sql = "select approve_reimburstment_request(?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, resolverID);
			ps.setInt(2, caseID);
			ps.execute();
			
			} catch (SQLException e) {
				log.fatal("A SQLException occurred.");
			}
		return true;
	}

	//admin
	public List<Reimbursement> getReimbursmentsByStatus(int statusID){
		
		List<Reimbursement> rList = new ArrayList<>();
		
		try(Connection con = DBCon.getDBConnection()){
			String sql = "select * from ers_reimbursement where reimb_status_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, statusID);
			ps.execute();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				rList.add(new Reimbursement (
						rs.getInt(1), rs.getTimestamp(2).toLocalDateTime(), rs.getTimestamp(3).toLocalDateTime(), 
						rs.getString(4), rs.getBlob(5), rs.getInt(6), 
						rs.getInt(7), rs.getInt(8), rs.getInt(9),  
						rs.getInt(10)));
			}
		} catch (SQLException e) {
			log.fatal("A SQLException occurred.");
		}
		System.out.println(rList.toString());
		log.info("reimb list @ getReimbursmentsByStatus: " + rList);
		return rList;
	}
	
	//admin
	public List<Reimbursement> getReimbursments(){
		
		List<Reimbursement> rList = new ArrayList<>();
		
		try(Connection con = DBCon.getDBConnection()){
			String sql = "select * from ers_reimbursement";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				rList.add(new Reimbursement (
						rs.getInt(1), 
						rs.getString(4), rs.getBlob(5), rs.getInt(6), 
						rs.getInt(7), rs.getInt(8), rs.getInt(9),  
						rs.getInt(10)));
			}
		} catch (SQLException e) {
			log.fatal("A SQLException occurred.");
		}
		System.out.println(rList.toString());
		log.info("reimb list @ getReimbursments: " + rList);
		return rList;
	}

}