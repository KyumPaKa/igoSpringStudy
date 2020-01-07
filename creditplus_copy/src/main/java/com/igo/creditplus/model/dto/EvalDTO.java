package com.igo.creditplus.model.dto;

import java.util.Date;

public class EvalDTO {

	private String evaid;
	private String accid;
	private String empno;
	private Long price;
	private Date evadate;
	private String accname;
	private int accgroup;
	
	public String getEvaid() {
		return evaid;
	}
	public void setEvaid(String evaid) {
		this.evaid = evaid;
	}
	public String getAccid() {
		return accid;
	}
	public void setAccid(String accid) {
		this.accid = accid;
	}
	public String getEmpno() {
		return empno;
	}
	public void setEmpno(String empno) {
		this.empno = empno;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Date getEvadate() {
		return evadate;
	}
	public void setEvadate(Date evadate) {
		this.evadate = evadate;
	}
	public String getAccname() {
		return accname;
	}
	public void setAccname(String accname) {
		this.accname = accname;
	}
	public int getAccgroup() {
		return accgroup;
	}
	public void setAccgroup(int accgroup) {
		this.accgroup = accgroup;
	}
	@Override
	public String toString() {
		return "EvalDTO [evaid=" + evaid + ", accid=" + accid + ", empno=" + empno + ", price=" + price + ", evadate="
				+ evadate + ", accname=" + accname + ", accgroup=" + accgroup + "]";
	}
	
}
