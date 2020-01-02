package com.igo.board.model.kim.dto;

import java.util.Date;

public class NoticeDTO {

	private int idx;
	private String refwriter;
	private String title;
	private String content;
	private Date postdate;
	private Date updatedate;
	private int viewcnt;
	private int npidx;
	private int refidx;
	private int refpoint;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getRefwriter() {
		return refwriter;
	}
	public void setRefwriter(String refwriter) {
		this.refwriter = refwriter;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPostdate() {
		return postdate;
	}
	public void setPostdate(Date postdate) {
		this.postdate = postdate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	public int getViewcnt() {
		return viewcnt;
	}
	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}
	public int getNpidx() {
		return npidx;
	}
	public void setNpidx(int npidx) {
		this.npidx = npidx;
	}
	public int getRefidx() {
		return refidx;
	}
	public void setRefidx(int refidx) {
		this.refidx = refidx;
	}
	public int getRefpoint() {
		return refpoint;
	}
	public void setRefpoint(int refpoint) {
		this.refpoint = refpoint;
	}
	
	@Override
	public String toString() {
		return "NoticeDTO [idx=" + idx + ", refwriter=" + refwriter + ", title=" + title + ", content=" + content
				+ ", postdate=" + postdate + ", updatedate=" + updatedate + ", viewcnt=" + viewcnt + ", npIdx=" + npidx
				+ ", refidx=" + refidx + ", refpoint=" + refpoint + "]";
	}
	
}
