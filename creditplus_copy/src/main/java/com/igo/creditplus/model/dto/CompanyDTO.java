package com.igo.creditplus.model.dto;

public class CompanyDTO {

	private String empno;
	private String empname;
	private String empdiv;
	private String modeldiv;
	private String grade;
	private String evastate;
	private String dat;
	
	public String getEmpno() {
		return empno;
	}
	public void setEmpno(String empno) {
		this.empno = empno;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public String getEmpdiv() {
		return empdiv;
	}
	public void setEmpdiv(String empdiv) {
		this.empdiv = empdiv;
	}
	public String getModeldiv() {
		return modeldiv;
	}
	public void setModeldiv(String modeldiv) {
		this.modeldiv = modeldiv;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getEvastate() {
		return evastate;
	}
	public void setEvastate(String evastate) {
		this.evastate = evastate;
	}
	public String getDat() {
		return dat;
	}
	public void setDat(String dat) {
		this.dat = dat;
	}
	@Override
	public String toString() {
		return "CompanyDTO [empno=" + empno + ", empname=" + empname + ", empdiv=" + empdiv + ", modeldiv=" + modeldiv
				+ ", grade=" + grade + ", evastate=" + evastate + ", dat=" + dat + "]";
	}	

}
