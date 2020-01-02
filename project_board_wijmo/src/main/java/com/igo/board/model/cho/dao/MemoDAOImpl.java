package com.igo.board.model.cho.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.igo.board.model.cho.dto.AttachDTO;
import com.igo.board.model.cho.dto.BoardDTO;
import com.igo.board.model.cho.dto.FeelDTO;

@Repository
public class MemoDAOImpl implements BoardDAO {

	@Resource
	SqlSession sqlSession;
	
	@Override
	public List<BoardDTO> list(int start, int end, String search_option, String keyword, int refIdx) {
		Map<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		map.put("refIdx", refIdx);
		return sqlSession.selectList("memo.list", map);
	}

	@Override
	public int countArticle(String search_option, String keyword, int refIdx) {
		return sqlSession.selectOne("memo.countArticle", refIdx);
	}
	
	@Override
	public int countArticle2(String search_option, String keyword, int refIdx) {
		return 0;
	}

	@Override
	public BoardDTO view(int idx) {
		return null;
	}
	
	@Override
	public int selectFeel(FeelDTO feelDTO) {
		Object result = sqlSession.selectOne("memo.selectFeel", feelDTO);
		return (result == null) ? 3 : (int)result;
	}

	@Override
	public void increaseViewCnt(int idx) {
		sqlSession.update("memo.increaseViewCnt", idx);
	}

	@Override
	public void increaseGoodCnt(int idx) {
		sqlSession.update("memo.increaseGoodCnt", idx);
	}

	@Override
	public void decreaseGoodCnt(int idx) {
		sqlSession.update("memo.decreaseGoodCnt", idx);
	}
	
	@Override
	public void increaseBadCnt(int idx) {
		sqlSession.update("memo.increaseBadCnt", idx);
	}
	
	@Override
	public void decreaseBadCnt(int idx) {
		sqlSession.update("memo.decreaseBadCnt", idx);
	}

	@Override
	public void insertFeel(FeelDTO feelDTO) {
		sqlSession.insert("memo.insertFeel", feelDTO);
	}

	@Override
	public int selectFeelCnt(FeelDTO feelDTO) {
		return sqlSession.selectOne("memo.selectFeelCnt", feelDTO);
	}

	@Override
	public void deleteFeel(FeelDTO feelDTO) {
		sqlSession.delete("memo.deleteFeel", feelDTO);
	}

	@Override
	public void delete(int idx) {
		sqlSession.update("memo.delete", idx);
	}

	@Override
	public List<AttachDTO> selectAttach(int refIdx) {
		return null;
	}

	@Override
	public void write(BoardDTO boardDTO) {
		sqlSession.insert("memo.write", boardDTO);
	}

	@Override
	public void insertAttach(AttachDTO attachDTO) {
		
	}
	
	@Override
	public void update(BoardDTO boardDTO) {
		sqlSession.update("memo.update", boardDTO);
	}
	
	@Override
	public int replyCount(int idx) {
		return 0;
	}

	@Override
	public void fileDelete(int idx) {
		
	}

	@Override
	public int secretCheck(int refIdx) {
		return 0;
	}

}
