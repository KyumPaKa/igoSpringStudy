package com.igo.board.model.cho.dao.suggestion;

import java.util.List;

import com.igo.board.model.cho.dto.suggestion.Sg_goodbadDTO;
import com.igo.board.model.cho.dto.suggestion.SuggestionDTO;

public interface SuggestionDAO {

	public List<SuggestionDTO> list(int start, int end, String search_option, String keyword);
	public void write(SuggestionDTO suggestionDTO);
	public void update(SuggestionDTO suggestionDTO);
	public void delete(int sg_idx);
	public SuggestionDTO view(int sg_idx);
	public int countArticle(String search_option, String keyword);
	public void increaseView(int sg_idx);
	public void increaseGood(int sg_idx);
	public void increaseBad(int sg_idx);
	public void insertGoodBad(Sg_goodbadDTO sg_goodbadDTO);
	public void deleteGoodBad(Sg_goodbadDTO sg_goodbadDTO);
	public int selectGood(Sg_goodbadDTO sg_goodbadDTO);
	public int selectBad(Sg_goodbadDTO sg_goodbadDTO);
	
}
