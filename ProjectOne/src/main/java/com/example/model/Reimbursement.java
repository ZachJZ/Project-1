package com.example.model;

import java.sql.Blob;
import java.time.LocalDateTime;

public class Reimbursement {

	private int amount;
	private LocalDateTime submit_time;
	private LocalDateTime resolve_time;
	private String description;
	
	//recipt bytea?
	private Blob recipt;
	
	private int author;
	private int resolver;
	
	private int status; //1:pending, 2:denied, 3:approved
	private int type; // 1: business, 2: personal
	
	private int reimb_id;
	
	
	public Reimbursement() {
		// TODO Auto-generated constructor stub
	}
	
	
	//Initial request maker
	public Reimbursement(int amount, String description, int author, int type) {
		super();
		this.amount = amount;
		this.submit_time = LocalDateTime.now(); //auto populate
		this.description = description;
		this.author = author; //set to the current logged user
		this.status = 1; //always starts as pending
		this.type = type;
		//this.reimb_id = reimb_id; //auto generated
	}
	
	//Approve/Deny setter
	public Reimbursement(LocalDateTime resolve_time, int resolver, int status) {
		super();
		this.resolve_time = LocalDateTime.now();;
		this.resolver = resolver; //should be set to current user which must be a manager
		this.status = status; //approved (3) or denied (2)
	}


	//full constructor
	public Reimbursement(int amount, LocalDateTime submit_time, LocalDateTime resolve_time, String description,
			Blob recipt, int author, int resolver, int status, int type, int reimb_id) {
		super();
		this.amount = amount;
		this.submit_time = submit_time;
		this.resolve_time = resolve_time;
		this.description = description;
		this.recipt = recipt;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
		this.reimb_id = reimb_id;
	}
	
	public Reimbursement(int amount, String description,
			Blob recipt, int author, int resolver, int status, int type, int reimb_id) {
		super();
		this.amount = amount;
		this.description = description;
		this.recipt = recipt;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
		this.reimb_id = reimb_id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public LocalDateTime getSubmit_time() {
		return submit_time;
	}

	public void setSubmit_time(LocalDateTime submit_time) {
		this.submit_time = submit_time;
	}

	public LocalDateTime getResolve_time() {
		return resolve_time;
	}

	public void setResolve_time(LocalDateTime resolve_time) {
		this.resolve_time = resolve_time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Blob getRecipt() {
		return recipt;
	}

	public void setRecipt(Blob recipt) {
		this.recipt = recipt;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public int getResolver() {
		return resolver;
	}

	public void setResolver(int resolver) {
		this.resolver = resolver;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getReimb_id() {
		return reimb_id;
	}

	@Override
	public String toString() {
		return "Reimbursement [amount=" + amount + ", submit_time=" + submit_time + ", resolve_time=" + resolve_time
				+ ", description=" + description + ", author=" + author + ", resolver=" + resolver + ", status="
				+ status + ", type=" + type + ", reimb_id=" + reimb_id + "]";
	}
	
}
