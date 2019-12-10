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
public class FreeBoardDAOImpl implements BoardDAO {

	@Resource
	SqlSession sqlSession;
	
	@Override
	public List<BoardDTO> list(int start, int end, String search_option, String keyword, int refIdx) {
		Map<String, Object> map = new HashMap<>();
		map.put("search_option", search_option);
		map.put("keyword", keyword);
		map.put("start", start);
		map.put("end", end);
		map.put("refIdx", refIdx);
		return sqlSession.selectList("freeBoard.list", map);
	}

	@Override
	public int countArticle(String search_option, String keyword, int refIdx) {
		Map<String, Object> map = new HashMap<>();
		map.put("search_option", search_option);
		map.put("keyword", keyword);
		map.put("refIdx", refIdx);
		return sqlSession.selectOne("freeBoard.countArticle", map);
	}
	
	@Override
	public int countArticle2(String search_option, String keyword, int refIdx) {
		Map<String, Object> map = new HashMap<>();
		map.put("search_option", search_option);
		map.put("keyword", keyword);
		map.put("refIdx", refIdx);
		return sqlSession.selectOne("freeBoard.countArticle2", map);
	}

	@Override
	public BoardDTO view(int idx) {
		return sqlSession.selectOne("freeBoard.view", idx);
	}
	
	@Override
	public int selectFeel(FeelDTO feelDTO) {
		Object result = sqlSession.selectOne("freeBoard.selectFeel", feelDTO);
		return (result == null) ? 3 : (int)result;
	}

	@Override
	public void increaseViewCnt(int idx) {
		sqlSession.update("freeBoard.increaseViewCnt", idx);
	}

	@Override
	public void increaseGoodCnt(int idx) {
		sqlSession.update("freeBoard.increaseGoodCnt", idx);
	}

	@Override
	public void decreaseGoodCnt(int idx) {
		sqlSession.update("freeBoard.decreaseGoodCnt", idx);
	}
	
	@Override
	public void increaseBadCnt(int idx) {
		sqlSession.update("freeBoard.increaseBadCnt", idx);
	}
	
	@Override
	public void decreaseBadCnt(int idx) {
		sqlSession.update("freeBoard.decreaseBadCnt", idx);
	}

	@Override
	public void insertFeel(FeelDTO feelDTO) {
		sqlSession.insert("freeBoard.insertFeel", feelDTO);
	}

	@Override
	public int selectFeelCnt(FeelDTO feelDTO) {
		return sqlSession.selectOne("freeBoard.selectFeelCnt", feelDTO);
	}

	@Override
	public void deleteFeel(FeelDTO feelDTO) {
		sqlSession.delete("freeBoard.deleteFeel", feelDTO);
	}

	@Override
	public void delete(int idx) {
		sqlSession.update("freeBoard.delete", idx);
	}

	@Override
	public List<AttachDTO> selectAttach(int refIdx) {
		return sqlSession.selectList("freeBoard.selectAttach", refIdx);
	}

	@Override
	public void write(BoardDTO boardDTO) {
		sqlSession.insert("freeBoard.write", boardDTO);
	}

	@Override
	public void insertAttach(AttachDTO attachDTO) {
		sqlSession.insert("freeBoard.insertAttach", attachDTO);
	}
	
	@Override
	public void update(BoardDTO boardDTO) {
		sqlSession.update("freeBoard.update", boardDTO);
	}
	
	@Override
	public int replyCount(int idx) {
		return sqlSession.selectOne("freeBoard.replyCount", idx);
	}

	@Override
	public void fileDelete(int idx) {
		sqlSession.delete("freeBoard.fileDelete", idx);
	}

	@Override
	public int secretCheck(int refIdx) {
		return sqlSession.selectOne("freeBoard.secretCheck", refIdx);
	}

}
