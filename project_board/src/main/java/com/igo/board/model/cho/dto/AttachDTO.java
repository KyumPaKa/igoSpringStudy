package com.igo.board.model.cho.dto;

public class AttachDTO {

	private int idx;
	private int refIdx;
	private String fileName;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getRefIdx() {
		return refIdx;
	}
	public void setRefIdx(int refIdx) {
		this.refIdx = refIdx;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Override
	public String toString() {
		return "AttachDTO [idx=" + idx + ", refIdx=" + refIdx + ", fileName=" + fileName + "]";
	}
	
}
