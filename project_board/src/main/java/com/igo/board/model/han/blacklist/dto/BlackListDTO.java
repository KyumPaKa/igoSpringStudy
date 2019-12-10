package com.igo.board.model.han.blacklist.dto;

import java.util.Date;

public class BlackListDTO {
	private int idx;
	private String refuserid;
	private String reason;
	private Date regdate;
	
	private String name;
	private String email;
	
	
	
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
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getRefuserid() {
		return refuserid;
	}
	public void setRefuserid(String refuserid) {
		this.refuserid = refuserid;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "BlackListDTO [idx=" + idx + ", refuserid=" + refuserid + ", reason=" + reason + ", regdate=" + regdate
				+ ", name=" + name + ", email=" + email + "]";
	}
	
	
	
}
