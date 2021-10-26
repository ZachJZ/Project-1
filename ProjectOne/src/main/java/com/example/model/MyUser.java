package com.example.model;

public class MyUser {

	private String username;
	private String password;
	private String fName;
	private String lName;
	private String email;
	private int roleID; //1 is employee, 2 is manager
	private int userID; 
	
	public MyUser() {
		// TODO Auto-generated constructor stub
	}

	public MyUser(String username, String password, String fName, String lName, String email, int roldID, int userID) {
		super();
		this.username = username;
		this.password = password;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.roleID = roldID;
		this.userID = userID;
	}
	
	public MyUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public int getRoleID() {
		return roleID;
	}

	public int getUserID() {
		return userID;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", fName=" + fName + ", lName=" + lName
				+ ", email=" + email + ", roldID=" + roleID + ", userID=" + userID + "]";
	}
}
