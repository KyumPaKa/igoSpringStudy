package com.igo.board.service.kim;

import java.util.List;

import com.igo.board.model.kim.dto.NoticeDTO;

public interface NoticeService {

	List<NoticeDTO> listSv();
	List<NoticeDTO> selectedListSv(int refIdx);
	List<NoticeDTO> viewSv(int idx);
	NoticeDTO selectedSv(int idx);
	void noticeUpdateSv(NoticeDTO dto);
	void noticeInsertSv(NoticeDTO dto);
	void noticeDeleteSv(int idx);
	
	List<NoticeDTO> npListSv();
	List<NoticeDTO> npviewSv(int idx);
	void noticePtUpdateSv(NoticeDTO dto);
	void noticePtInsertSv(NoticeDTO dto);
	void noticePtDeleteSv(int idx);
	
	List<NoticeDTO> noticeGetIdx();
	void noticeVcSv(int idx);
}
