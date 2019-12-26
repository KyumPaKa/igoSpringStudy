package com.igo.board.model.cho.dto.suggestion;

import java.util.Date;

public class Sg_replyDTO {

	private int sg_rp_idx; // 인덱스
	private int sg_rp_group; // 인덱스 그룹
	private int sg_rp_groupNo; // 인덱스 그룹 순서
	private int sg_rp_depth; // 들여쓰기
	private String sg_rp_content; // 내용
	private String sg_rp_replyer; // 작성자
	private Date sg_rp_regDate; // 작성날짜
	private Date sg_rp_updateDate; // 수정날짜
	private int sg_rp_good; // 좋아요 개수
	private int sg_rp_bad; // 싫어요 개수
	public int getSg_rp_idx() {
		return sg_rp_idx;
	}
	public void setSg_rp_idx(int sg_rp_idx) {
		this.sg_rp_idx = sg_rp_idx;
	}
	public int getSg_rp_group() {
		return sg_rp_group;
	}
	public void setSg_rp_group(int sg_rp_group) {
		this.sg_rp_group = sg_rp_group;
	}
	public int getSg_rp_groupNo() {
		return sg_rp_groupNo;
	}
	public void setSg_rp_groupNo(int sg_rp_groupNo) {
		this.sg_rp_groupNo = sg_rp_groupNo;
	}
	public int getSg_rp_depth() {
		return sg_rp_depth;
	}
	public void setSg_rp_depth(int sg_rp_depth) {
		this.sg_rp_depth = sg_rp_depth;
	}
	public String getSg_rp_content() {
		return sg_rp_content;
	}
	public void setSg_rp_content(String sg_rp_content) {
		this.sg_rp_content = sg_rp_content;
	}
	public String getSg_rp_replyer() {
		return sg_rp_replyer;
	}
	public void setSg_rp_replyer(String sg_rp_replyer) {
		this.sg_rp_replyer = sg_rp_replyer;
	}
	public Date getSg_rp_regDate() {
		return sg_rp_regDate;
	}
	public void setSg_rp_regDate(Date sg_rp_regDate) {
		this.sg_rp_regDate = sg_rp_regDate;
	}
	public Date getSg_rp_updateDate() {
		return sg_rp_updateDate;
	}
	public void setSg_rp_updateDate(Date sg_rp_updateDate) {
		this.sg_rp_updateDate = sg_rp_updateDate;
	}
	public int getSg_rp_good() {
		return sg_rp_good;
	}
	public void setSg_rp_good(int sg_rp_good) {
		this.sg_rp_good = sg_rp_good;
	}
	public int getSg_rp_bad() {
		return sg_rp_bad;
	}
	public void setSg_rp_bad(int sg_rp_bad) {
		this.sg_rp_bad = sg_rp_bad;
	}
	@Override
	public String toString() {
		return "sg_replyDTO [sg_rp_idx=" + sg_rp_idx + ", sg_rp_group=" + sg_rp_group + ", sg_rp_groupNo="
				+ sg_rp_groupNo + ", sg_rp_depth=" + sg_rp_depth + ", sg_rp_content=" + sg_rp_content
				+ ", sg_rp_replyer=" + sg_rp_replyer + ", sg_rp_regDate=" + sg_rp_regDate + ", sg_rp_updateDate="
				+ sg_rp_updateDate + ", sg_rp_good=" + sg_rp_good + ", sg_rp_bad=" + sg_rp_bad + "]";
	}
	
}
