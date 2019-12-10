package com.igo.board.model.cho.dto;

import java.util.Date;
import java.util.List;

public class BoardDTO {

	private int rn;
	private int idx;
	private int refIdx;
	private String refWriter;
	private String title;
	private String content;
	private Date postDate;
	private Date updateDate;
	private int goodCnt;
	private int badCnt;
	private int viewCnt;
	private int secretCheck;
	private int deleteCheck;
	private List<AttachDTO> attachList;
	private List<ReplyDTO> replyList;
	private int replyCnt;
	private int feel;
	
	public int getRn() {
		return rn;
	}
	public void setRn(int rn) {
		this.rn = rn;
	}
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
	public String getRefWriter() {
		return refWriter;
	}
	public void setRefWriter(String refWriter) {
		this.refWriter = refWriter;
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
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public int getGoodCnt() {
		return goodCnt;
	}
	public void setGoodCnt(int goodCnt) {
		this.goodCnt = goodCnt;
	}
	public int getBadCnt() {
		return badCnt;
	}
	public void setBadCnt(int badCnt) {
		this.badCnt = badCnt;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	public int getSecretCheck() {
		return secretCheck;
	}
	public void setSecretCheck(int secretCheck) {
		this.secretCheck = secretCheck;
	}
	public int getDeleteCheck() {
		return deleteCheck;
	}
	public void setDeleteCheck(int deleteCheck) {
		this.deleteCheck = deleteCheck;
	}
	public List<AttachDTO> getAttachList() {
		return attachList;
	}
	public void setAttachList(List<AttachDTO> attachList) {
		this.attachList = attachList;
	}
	public List<ReplyDTO> getReplyList() {
		return replyList;
	}
	public void setReplyList(List<ReplyDTO> replyList) {
		this.replyList = replyList;
	}
	public int getReplyCnt() {
		return replyCnt;
	}
	public void setReplyCnt(int replyCnt) {
		this.replyCnt = replyCnt;
	}
	public int getFeel() {
		return feel;
	}
	public void setFeel(int feel) {
		this.feel = feel;
	}
	@Override
	public String toString() {
		return "BoardDTO [rn=" + rn + ", idx=" + idx + ", refIdx=" + refIdx + ", refWriter=" + refWriter + ", title="
				+ title + ", content=" + content + ", postDate=" + postDate + ", updateDate=" + updateDate
				+ ", goodCnt=" + goodCnt + ", badCnt=" + badCnt + ", viewCnt=" + viewCnt + ", secretCheck="
				+ secretCheck + ", deleteCheck=" + deleteCheck + ", attachList=" + attachList + ", replyList="
				+ replyList + ", replyCnt=" + replyCnt + ", feel=" + feel + "]";
	}
	
}
