package com.example.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

import jdk.internal.net.http.common.Log;

public class DBConnection {

	// this is a utility class that has the sole purpose of creating connections to the database
	// this eliminates some repeated code and makes our code more testable (because of the driver manager class)
	
	ClassLoader classLoader = getClass().getClassLoader();
	InputStream is; 
	Properties p = new Properties();
	
	public DBConnection() {
		// TODO Auto-generated constructor stub
		is = classLoader.getResourceAsStream("connection.properties");
		try {
			p.load(is);
		} catch (IOException e) {
			Log.logError("Hit an error!" , e);
		}
	}
	
	public Connection getDBConnection() throws SQLException, NullPointerException{
		
		System.out.println("inside of the getDBConnection script");
		final String URL = p.getProperty("url");
		final String USERNAME = p.getProperty("username");
		final String PASSWORD = p.getProperty("password");
		
		try {
            Class.forName("org.postgresql.Driver");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
            
        }
		
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		
	}
}
