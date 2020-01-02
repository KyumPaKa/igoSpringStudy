package com.igo.board.model.han.blacklist.dto;

import java.util.Date;

public class BoardUserDTO {
	private String userid;
	private String passwd;
	private String name;
	private String email;
	private String address;
	private Date joinDate;
	private char enable;
	private String authority;
	private int board;
	public int getBoard() {
		return board;
	}
	public void setBoard(int board) {
		this.board = board;
	}

	private int rply;
	
	
	
	public int getRply() {
		return rply;
	}
	public void setRply(int rply) {
		this.rply = rply;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public char getEnable() {
		return enable;
	}
	public void setEnable(char enable) {
		this.enable = enable;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	@Override
	public String toString() {
		return "BoardUserDTO [userid=" + userid + ", passwd=" + passwd + ", name=" + name + ", email=" + email
				+ ", address=" + address + ", joinDate=" + joinDate + ", enable=" + enable + ", authority=" + authority
				+ ", board=" + board + ", rply=" + rply + "]";
	}
	
	
}
