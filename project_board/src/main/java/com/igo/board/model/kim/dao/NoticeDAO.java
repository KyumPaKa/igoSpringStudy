package com.igo.board.model.kim.dao;

import java.util.List;

import com.igo.board.model.kim.dto.NoticeDTO;

public interface NoticeDAO {

	List<NoticeDTO> listDao();
	List<NoticeDTO> selectedListDao(int refIdx);
	List<NoticeDTO> viewDao(int idx);
	NoticeDTO selectedDao(int idx);
	void noticeUpdateDao(NoticeDTO dto);
	void noticeInsertDao(NoticeDTO dto);
	void noticeDeleteDao(int idx);
	
	List<NoticeDTO> npListDao();
	List<NoticeDTO> npviewDao(int idx);
	void noticePtUpdateDao(NoticeDTO dto);
	void noticePtInsertDao(NoticeDTO dto);
	void noticePtDeleteDao(int idx);
	
	List<NoticeDTO> noticeGetIdx();
	void noticeVcDao(int idx);
}
