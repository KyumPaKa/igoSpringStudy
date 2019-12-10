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
public class UnknownBoardDAOImpl implements BoardDAO {

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
		return sqlSession.selectList("unknownBoard.list", map);
	}

	@Override
	public int countArticle(String search_option, String keyword, int refIdx) {
		Map<String, Object> map = new HashMap<>();
		map.put("search_option", search_option);
		map.put("keyword", keyword);
		map.put("refIdx", refIdx);
		return sqlSession.selectOne("unknownBoard.countArticle", map);
	}
	
	@Override
	public int countArticle2(String search_option, String keyword, int refIdx) {
		Map<String, Object> map = new HashMap<>();
		map.put("search_option", search_option);
		map.put("keyword", keyword);
		map.put("refIdx", refIdx);
		return sqlSession.selectOne("unknownBoard.countArticle2", map);
	}

	@Override
	public BoardDTO view(int idx) {
		return sqlSession.selectOne("unknownBoard.view", idx);
	}
	
	@Override
	public int selectFeel(FeelDTO feelDTO) {
		Object result = sqlSession.selectOne("unknownBoard.selectFeel", feelDTO);
		return (result == null) ? 3 : (int)result;
	}

	@Override
	public void increaseViewCnt(int idx) {
		sqlSession.update("unknownBoard.increaseViewCnt", idx);
	}

	@Override
	public void increaseGoodCnt(int idx) {
		sqlSession.update("unknownBoard.increaseGoodCnt", idx);
	}

	@Override
	public void decreaseGoodCnt(int idx) {
		sqlSession.update("unknownBoard.decreaseGoodCnt", idx);
	}
	
	@Override
	public void increaseBadCnt(int idx) {
		sqlSession.update("unknownBoard.increaseBadCnt", idx);
	}
	
	@Override
	public void decreaseBadCnt(int idx) {
		sqlSession.update("unknownBoard.decreaseBadCnt", idx);
	}

	@Override
	public void insertFeel(FeelDTO feelDTO) {
		sqlSession.insert("unknownBoard.insertFeel", feelDTO);
	}

	@Override
	public int selectFeelCnt(FeelDTO feelDTO) {
		return sqlSession.selectOne("unknownBoard.selectFeelCnt", feelDTO);
	}

	@Override
	public void deleteFeel(FeelDTO feelDTO) {
		sqlSession.delete("unknownBoard.deleteFeel", feelDTO);
	}

	@Override
	public void delete(int idx) {
		sqlSession.update("unknownBoard.delete", idx);
	}

	@Override
	public List<AttachDTO> selectAttach(int refIdx) {
		return sqlSession.selectList("unknownBoard.selectAttach", refIdx);
	}

	@Override
	public void write(BoardDTO boardDTO) {
		sqlSession.insert("unknownBoard.write", boardDTO);
	}

	@Override
	public void insertAttach(AttachDTO attachDTO) {
		sqlSession.insert("unknownBoard.insertAttach", attachDTO);
	}
	
	@Override
	public void update(BoardDTO boardDTO) {
		sqlSession.update("unknownBoard.update", boardDTO);
	}
	@Override
	public int replyCount(int idx) {
		return sqlSession.selectOne("unknownBoard.replyCount", idx);
	}

	@Override
	public void fileDelete(int idx) {
		sqlSession.delete("unknownBoard.fileDelete", idx);
	}

	@Override
	public int secretCheck(int refIdx) {
		return sqlSession.selectOne("unknownBoard.secretCheck", refIdx);
	}
	
}
