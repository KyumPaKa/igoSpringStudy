package com.igo.creditplus.model.dto;

import java.util.Date;

public class HistoryDTO {

	private int empno;
	private int hisno;
	private String hiscontent;
	private String hisstr;
	private Date hisdate;
	
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public int getHisno() {
		return hisno;
	}
	public void setHisno(int hisno) {
		this.hisno = hisno;
	}
	public String getHiscontent() {
		return hiscontent;
	}
	public void setHiscontent(String hiscontent) {
		this.hiscontent = hiscontent;
	}
	public String getHisstr() {
		return hisstr;
	}
	public void setHisstr(String hisstr) {
		this.hisstr = hisstr;
	}
	public Date getHisdate() {
		return hisdate;
	}
	public void setHisdate(Date hisdate) {
		this.hisdate = hisdate;
	}
	@Override
	public String toString() {
		return "HistoryDTO [empno=" + empno + ", hisno=" + hisno + ", hiscontent=" + hiscontent + ", hisstr=" + hisstr
				+ ", hisdate=" + hisdate + "]";
	}
	
}
