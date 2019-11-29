package com.igo.board.model.cho.dao.suggestion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.igo.board.model.cho.dto.suggestion.Sg_goodbadDTO;
import com.igo.board.model.cho.dto.suggestion.SuggestionDTO;

@Repository
public class SuggestionDAOImpl implements SuggestionDAO {

	@Autowired
	SqlSession sqlSession;

	@Override
	public List<SuggestionDTO> list(int start, int end, String search_option, String keyword) {
		Map<String, Object> map = new HashMap<>();
		map.put("search_option", search_option);
		map.put("keyword", keyword);
		map.put("start", start);
		map.put("end", end);
		return sqlSession.selectList("suggestion.list", map);
	}

	@Override
	public void write(SuggestionDTO suggestionDTO) {
		sqlSession.insert("suggestion.write", suggestionDTO);
	}

	@Override
	public void update(SuggestionDTO suggestionDTO) {		
		sqlSession.insert("suggestion.update", suggestionDTO);
	}

	@Override
	public void delete(int sg_idx) {
		sqlSession.delete("suggestion.delete", sg_idx);
	}

	@Override
	public SuggestionDTO view(int sg_idx) {
		return sqlSession.selectOne("suggestion.view", sg_idx);
	}

	@Override
	public int countArticle(String search_option, String keyword) {
		Map<String, Object> map = new HashMap<>();
		map.put("search_option", search_option);
		map.put("keyword", keyword);
		return sqlSession.selectOne("suggestion.countArticle", map);
	}

	@Override
	public void increaseView(int sg_idx) {
		sqlSession.update("suggestion.increaseView", sg_idx);
	}

	@Override
	public void increaseGood(int sg_idx) {
		sqlSession.update("suggestion.increaseGood", sg_idx);
	}

	@Override
	public void increaseBad(int sg_idx) {		
		sqlSession.update("suggestion.increaseBad", sg_idx);
	}

	@Override
	public void insertGoodBad(Sg_goodbadDTO sg_goodbadDTO) {
		sqlSession.update("suggestion.insertGoodBad", sg_goodbadDTO);		
	}
	
	@Override
	public void deleteGoodBad(Sg_goodbadDTO sg_goodbadDTO) {
		sqlSession.delete("suggestion.deleteGoodBad", sg_goodbadDTO);
	}
	
	@Override
	public int selectGood(Sg_goodbadDTO sg_goodbadDTO) {
		return sqlSession.selectOne("suggestion.selectGood", sg_goodbadDTO);
	}
	
	@Override
	public int selectBad(Sg_goodbadDTO sg_goodbadDTO) {
		return sqlSession.selectOne("suggestion.selectBad", sg_goodbadDTO);
	}
	
}
