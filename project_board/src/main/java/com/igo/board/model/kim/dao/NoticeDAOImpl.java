package com.igo.board.model.kim.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.igo.board.model.kim.dto.NoticeDTO;

@Repository
public class NoticeDAOImpl implements NoticeDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<NoticeDTO> listDao() {
		return sqlSession.selectList("noticeListMP");
	}

	@Override
	public List<NoticeDTO> selectedListDao(int refIdx) {
		return sqlSession.selectList("selectedListMP", refIdx);
	}
	
	@Override
	public List<NoticeDTO> viewDao(int idx) {
		return sqlSession.selectList("noticeOneMP", idx);
	}
	
	@Override
	public NoticeDTO selectedDao(int idx) {
		return sqlSession.selectOne("noticeSelectedMP", idx);
	}

	@Override
	public void noticeUpdateDao(NoticeDTO dto) {
		sqlSession.update("noticeUpdateMP", dto);

	}

	@Override
	public void noticeInsertDao(NoticeDTO dto) {
		sqlSession.insert("noticeInsertMP", dto);
	}

	@Override
	public void noticeDeleteDao(int idx) {
		sqlSession.delete("noticeDeleteMP", idx);
	}

	// noticePt
	
	@Override
	public void noticePtUpdateDao(NoticeDTO dto) {
		sqlSession.update("noticePtUpdateMP", dto);
	}

	@Override
	public void noticePtInsertDao(NoticeDTO dto) {
		sqlSession.update("noticePtInsertMP", dto);
	}

	@Override
	public void noticePtDeleteDao(int idx) {
		sqlSession.delete("noticePtDeleteMP", idx);
	}

	@Override
	public List<NoticeDTO> npListDao() {
		return sqlSession.selectList("noticePtListMP");
	}
	
	@Override
	public List<NoticeDTO> npviewDao(int idx) {
		return sqlSession.selectList("noticePtOneMP");
	}
	
	@Override
	public List<NoticeDTO> noticeGetIdx() {
		return sqlSession.selectList("noticeGetIdx");
	}

	@Override
	public void noticeVcDao(int idx) {
		sqlSession.update("noticeVc", idx);
	}

}
