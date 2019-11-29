package com.igo.board.service.cho.suggestion;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.igo.board.model.cho.dto.suggestion.SuggestionDTO;

public interface SuggestionService {

	public List<SuggestionDTO> list(int start, int end, String search_option, String keyword);
	public void write(SuggestionDTO suggestionDTO);
	public SuggestionDTO updateForm(int sg_idx);
	public void update(SuggestionDTO suggestionDTO);
	public void delete(int sg_idx);
	public SuggestionDTO view(int sg_idx, HttpServletResponse response, HttpServletRequest request);
	public int countArticle(String search_option, String keyword);
	public String imgUpload(MultipartHttpServletRequest mRequest);
	public int increaseGood(int sg_idx, HttpServletRequest request);
	public int increaseBad(int sg_idx, HttpServletRequest request);
	
}
