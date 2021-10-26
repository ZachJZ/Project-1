package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.example.model.MyUser;

public class MyUserDaoImpl {

	/*
	 * My stored functions:
	 * 
	 * 		submit_reimburstment_request(r_amount int, r_desc varchar(250), r_author int, r_type int)
	 * 		process_reimburstment_request(r_resolver int, r_approved int, r_id int) --2 for approved, 3 for denied
	 * 		deny_reimburstment_request(r_resolver int, r_id int) --2 for approved, 3 for denied
	 * 		approve_reimburstment_request(r_resolver int, r_id int) --2 for approved, 3 for denied
	 * 		insert_user(u_username varchar(50), u_password varchar(50), u_firstName varchar(100), u_lastName varchar(100), u_email varchar(150))
	 * 		
	 */
	
	//private List<MyUser> uList;
	private DBConnection DBCon;
	//private DBConnection DBCon = new DBConnection();
	
	private static final Logger log = Logger.getLogger(MyUserDaoImpl.class);

	
	public MyUserDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public MyUserDaoImpl(DBConnection aDBCon) {
		System.out.println("did the daoimpl dbcon");
		this.DBCon = aDBCon;
	}
	
	public List<MyUser> getAllUsers(){
		
		List<MyUser> uList = new ArrayList<>();
		
		try(Connection con = DBCon.getDBConnection()){
			
			String sql = "select * from ers_users";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {	 
				uList.add( new MyUser (rs.getString(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7)));
			}
			
		} catch (SQLException e) {
			log.fatal("A SQLException occurred.");
		}
		log.info("user list @ getAllUsers: " + uList);
		return uList;
	}
	
	public MyUser getUserByName(String username) {
		
		MyUser mUser = null;
		System.out.println("in the getByName");
		
		
		try(Connection con = DBCon.getDBConnection()){
			
			String sql = "select * from ers_users u where ers_username like '" + username + "'";
			PreparedStatement ps = con.prepareStatement(sql);
			//ps.setString(1, username);
			ps.execute();
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {	 
				mUser = new MyUser(rs.getString(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7));
			}
			
		} catch (SQLException e) {
			log.fatal("A SQLException occurred.");
		}
		log.info("my user @ getUsersByName: " + mUser);
		return mUser;
	}
	
	public MyUser validateManager(String username) {
		
		MyUser adminUser = null;
		
		try(Connection con = DBCon.getDBConnection()){
			String sql = "select * from ers_users u where username like '" + username + "' and user_role_id = 2";
			PreparedStatement ps = con.prepareStatement(sql);
			//ps.setString(1, username);
			ps.execute();
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {	 
				adminUser = new MyUser(rs.getString(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7));
			}
			
		} catch (SQLException e) {
			log.fatal("A SQLException occurred.");
		}
		log.info("admin user @ validateManager: " + adminUser);
		return adminUser;
	}
}
