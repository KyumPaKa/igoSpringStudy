package com.example.spring03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller // 컨트롤러 빈으로 등록
public class HelloController {

	@RequestMapping("/") // 시작페이지
	public ModelAndView hello(ModelAndView mav) {
		mav.setViewName("hello"); // 뷰의 이름(페이지 이름)
		mav.addObject("message", "스프링 부트 애플리케이션"); // 데이터 저장
		return mav; // 화면 출력
	}
}
