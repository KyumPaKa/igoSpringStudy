package com.example.spring03.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring03.model.dto.GuestbookDTO;
import com.example.spring03.service.GuestbookService;

@Controller
public class GuestbookController {

	@Inject
	GuestbookService guestbookService;
	
	@RequestMapping("list.do")
	public ModelAndView main(ModelAndView mav) {
		mav.setViewName("list"); // 뷰의 이름
		List<GuestbookDTO> list = guestbookService.list();
		mav.addObject("list", list); // 데이터 저장
		return mav; // 화면 출력
	}
	
}
