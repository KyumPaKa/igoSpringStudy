package com.igo.board.model.kim.dto;

public class CategoryDTO {

	private int idx;
	private int orderno;
	private int cgidx;
	private String name;
	private int secretcheck;
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getOrderno() {
		return orderno;
	}
	public void setOrderno(int orderno) {
		this.orderno = orderno;
	}
	public int getcgidx() {
		return cgidx;
	}
	public void setcgidx(int cgidx) {
		this.cgidx = cgidx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSecretcheck() {
		return secretcheck;
	}
	public void setSecretcheck(int secretcheck) {
		this.secretcheck = secretcheck;
	}
	
	@Override
	public String toString() {
		return "CategoryDTO [idx=" + idx + ", orderno=" + orderno + ", cgidx=" + cgidx + ", name=" + name
				+ ", secretcheck=" + secretcheck + "]";
	}
	
}
