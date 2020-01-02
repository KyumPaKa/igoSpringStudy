package com.igo.board.model.cho.dto;

import java.util.Date;

public class ReplyDTO {

	private int idx;
	private int refIdx;
	private int groupIdx;
	private int groupNo;
	private int depth;
	private String content;
	private String refWriter;
	private Date regDate;
	private Date updateDate;
	private int deleteCheck;
	
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
	public int getGroupIdx() {
		return groupIdx;
	}
	public void setGroupIdx(int groupIdx) {
		this.groupIdx = groupIdx;
	}
	public int getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getrefWriter() {
		return refWriter;
	}
	public void setrefWriter(String refWriter) {
		this.refWriter = refWriter;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public int getDeleteCheck() {
		return deleteCheck;
	}
	public void setDeleteCheck(int deleteCheck) {
		this.deleteCheck = deleteCheck;
	}
	@Override
	public String toString() {
		return "ReplyDTO [idx=" + idx + ", refIdx=" + refIdx + ", groupIdx=" + groupIdx + ", groupNo=" + groupNo
				+ ", depth=" + depth + ", content=" + content + ", refWriter=" + refWriter + ", regDate=" + regDate
				+ ", updateDate=" + updateDate + ", deleteCheck=" + deleteCheck + "]";
	}
	
}
