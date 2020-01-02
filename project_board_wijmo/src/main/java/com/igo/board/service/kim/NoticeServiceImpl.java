package com.igo.board.service.kim;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.igo.board.model.kim.dao.NoticeDAOImpl;
import com.igo.board.model.kim.dto.NoticeDTO;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Inject
	NoticeDAOImpl noticeDAOImpl;
	
	@Override
	public List<NoticeDTO> listSv() {
		return noticeDAOImpl.listDao();
	}
	
	@Override
	public List<NoticeDTO> selectedListSv(int refIdx) {
		return noticeDAOImpl.selectedListDao(refIdx);
	}

	@Override
	public List<NoticeDTO> viewSv(int idx) {
		return noticeDAOImpl.viewDao(idx);
	}
	
	@Override
	public NoticeDTO selectedSv(int idx) {
		return noticeDAOImpl.selectedDao(idx);
	}

	@Override
	public void noticeUpdateSv(NoticeDTO dto) {
		noticeDAOImpl.noticeUpdateDao(dto);
	}

	@Override
	public void noticeInsertSv(NoticeDTO dto) {
		noticeDAOImpl.noticeInsertDao(dto);
	}

	@Override
	public void noticeDeleteSv(int idx) {
		noticeDAOImpl.noticeDeleteDao(idx);
	}

	@Override
	public void noticePtUpdateSv(NoticeDTO dto) {
		noticeDAOImpl.noticePtUpdateDao(dto);
	}

	@Override
	public void noticePtInsertSv(NoticeDTO dto) {
		noticeDAOImpl.noticePtInsertDao(dto);
	}

	@Override
	public void noticePtDeleteSv(int idx) {
		noticeDAOImpl.noticePtDeleteDao(idx);
	}

	@Override
	public List<NoticeDTO> npListSv() {
		return noticeDAOImpl.npListDao();
	}
	
	@Override
	public List<NoticeDTO> npviewSv(int idx) {
		return noticeDAOImpl.npviewDao(idx);
	}

	@Override
	public List<NoticeDTO> noticeGetIdx() {
		return noticeDAOImpl.noticeGetIdx();
	}

	@Override
	public void noticeVcSv(int idx) {
		noticeDAOImpl.noticeVcDao(idx);
	}

}
