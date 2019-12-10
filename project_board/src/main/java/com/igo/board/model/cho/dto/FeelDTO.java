package com.igo.board.model.cho.dto;

public class FeelDTO {

	private int refIdx;
	private String refUserid;
	private int feel;
	
	public int getRefIdx() {
		return refIdx;
	}
	public void setRefIdx(int refIdx) {
		this.refIdx = refIdx;
	}
	public String getRefUserid() {
		return refUserid;
	}
	public void setRefUserid(String refUserid) {
		this.refUserid = refUserid;
	}
	public int getFeel() {
		return feel;
	}
	public void setFeel(int feel) {
		this.feel = feel;
	}
	@Override
	public String toString() {
		return "FeelDTO [refIdx=" + refIdx + ", refUserid=" + refUserid + ", feel=" + feel + "]";
	}
	
}
