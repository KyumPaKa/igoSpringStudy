package com.example.spring05.model.dao;

import java.util.List;

import com.example.spring05.model.dto.AnswerDTO;
import com.example.spring05.model.dto.SurveyDTO;

// dao의 인터페이스
public interface SurveyDAO {

	public SurveyDTO showSurvey(int survey_idx); // 문제보기
	public void save(AnswerDTO dto); // 응답 내용 저장
	public List<AnswerDTO> showResult(int survey_idx); // 설문 결과 목록
	
}
