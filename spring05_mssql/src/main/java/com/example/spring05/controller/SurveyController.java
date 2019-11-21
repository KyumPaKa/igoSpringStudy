package com.example.spring05.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring05.model.dao.SurveyDAO;
import com.example.spring05.model.dto.AnswerDTO;
import com.example.spring05.model.dto.SurveyDTO;

@Controller // controller bean
public class SurveyController {

	@Autowired // 의존관계 주입
	SurveyDAO surveyDao;
	
	@RequestMapping("/") // 문제 화면 출력
	public ModelAndView show_survey(HttpServletRequest request) {
		System.out.println(request.getRealPath("/"));
		System.out.println(request.getContextPath());
		System.out.println(request.getServletPath());
		System.out.println(request.getRequestURI());
		System.out.println(request.getRequestURL());
		SurveyDTO dto = surveyDao.showSurvey(1);
		return new ModelAndView("main", "dto", dto);
	}
	
	@RequestMapping("/save_survey.do") // 응답 저장
	public String save_survey(AnswerDTO dto) {
		surveyDao.save(dto);
		return "success";
	}
	
	@RequestMapping("/show_result.do") // 설문 결과
	public ModelAndView show_result() {
		Map<String, Object> map = new HashMap<>();
		SurveyDTO dto = surveyDao.showSurvey(1);
		List<AnswerDTO> items = surveyDao.showResult(1);
		map.put("dto", dto);
		map.put("list", items);
		return new ModelAndView("result", "map", map);
	}
	
}
