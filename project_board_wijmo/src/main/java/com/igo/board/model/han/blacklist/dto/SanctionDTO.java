package com.igo.board.model.han.blacklist.dto;

public class SanctionDTO {
	private int idx;
	private int refboardcategory;
	private int refidx;
	private String refuserid;
	private String category;
	private int count;
	private String status;
	
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
	public int getRefboardcategory() {
		return refboardcategory;
	}
	public void setRefboardcategory(int refboardcategory) {
		this.refboardcategory = refboardcategory;
	}
	public int getRefidx() {
		return refidx;
	}
	public void setRefidx(int refidx) {
		this.refidx = refidx;
	}
	public String getRefuserid() {
		return refuserid;
	}
	public void setRefuserid(String refuserid) {
		this.refuserid = refuserid;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "SanctionDTO [idx=" + idx + ", refboardcategory=" + refboardcategory + ", refidx=" + refidx
				+ ", refuserid=" + refuserid + ", category=" + category + ", count=" + count + ", status=" + status
				+ ", name=" + name + ", email=" + email + "]";
	}
	
	
	
}
