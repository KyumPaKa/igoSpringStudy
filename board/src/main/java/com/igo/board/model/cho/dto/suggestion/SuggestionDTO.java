package com.igo.board.model.cho.dto.suggestion;

import java.util.Date;
import java.util.List;

public class SuggestionDTO {

	private int sg_idx; // 인덱스
	private String sg_writer; // 작성자
	private String sg_title; // 제목
	private String sg_content; // 내용
	private String sg_file; // 업로드 파일명
	private Date sg_postDate; // 작성날짜
	private Date sg_updateDate; // 최근수정날짜
	private int sg_good; // 좋아요개수
	private int sg_bad; // 싫어요개수
	private int sg_view; // 조회수
	private List<Sg_replyDTO> sg_replyDTOList; // 댓글
	public int getSg_idx() {
		return sg_idx;
	}
	public void setSg_idx(int sg_idx) {
		this.sg_idx = sg_idx;
	}
	public String getSg_writer() {
		return sg_writer;
	}
	public void setSg_writer(String sg_writer) {
		this.sg_writer = sg_writer;
	}
	public String getSg_title() {
		return sg_title;
	}
	public void setSg_title(String sg_title) {
		this.sg_title = sg_title;
	}
	public String getSg_content() {
		return sg_content;
	}
	public void setSg_content(String sg_content) {
		this.sg_content = sg_content;
	}
	public String getSg_file() {
		return sg_file;
	}
	public void setSg_file(String sg_file) {
		this.sg_file = sg_file;
	}
	public Date getSg_postDate() {
		return sg_postDate;
	}
	public void setSg_postDate(Date sg_postDate) {
		this.sg_postDate = sg_postDate;
	}
	public Date getSg_updateDate() {
		return sg_updateDate;
	}
	public void setSg_updateDate(Date sg_updateDate) {
		this.sg_updateDate = sg_updateDate;
	}
	public int getSg_good() {
		return sg_good;
	}
	public void setSg_good(int sg_good) {
		this.sg_good = sg_good;
	}
	public int getSg_bad() {
		return sg_bad;
	}
	public void setSg_bad(int sg_bad) {
		this.sg_bad = sg_bad;
	}
	public int getSg_view() {
		return sg_view;
	}
	public void setSg_view(int sg_view) {
		this.sg_view = sg_view;
	}
	public List<Sg_replyDTO> getSg_replyDTOList() {
		return sg_replyDTOList;
	}
	public void setSg_replyDTOList(List<Sg_replyDTO> sg_replyDTOList) {
		this.sg_replyDTOList = sg_replyDTOList;
	}
	@Override
	public String toString() {
		return "SuggestionDTO [sg_idx=" + sg_idx + ", sg_writer=" + sg_writer + ", sg_title=" + sg_title
				+ ", sg_content=" + sg_content + ", sg_file=" + sg_file + ", sg_postDate=" + sg_postDate
				+ ", sg_updateDate=" + sg_updateDate + ", sg_good=" + sg_good + ", sg_bad=" + sg_bad + ", sg_view="
				+ sg_view + ", sg_replyDTOList=" + sg_replyDTOList + ", getSg_idx()=" + getSg_idx()
				+ ", getSg_writer()=" + getSg_writer() + ", getSg_title()=" + getSg_title() + ", getSg_content()="
				+ getSg_content() + ", getSg_file()=" + getSg_file() + ", getSg_postDate()=" + getSg_postDate()
				+ ", getSg_updateDate()=" + getSg_updateDate() + ", getSg_good()=" + getSg_good() + ", getSg_bad()="
				+ getSg_bad() + ", getSg_view()=" + getSg_view() + ", getSg_replyDTOList()=" + getSg_replyDTOList()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
}
