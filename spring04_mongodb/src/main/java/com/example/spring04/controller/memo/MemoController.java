package com.example.spring04.controller.memo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring04.model.memo.dto.MemoDTO;
import com.example.spring04.service.memo.MemoService;

@Controller
public class MemoController {

	@Autowired
	MemoService memoService;
	
	@RequestMapping("/memo.do")
	public ModelAndView memo() {
		return new ModelAndView("memo/memo");
	}
	
	@RequestMapping("/memo_list.do")
	public ModelAndView memo_list() {
		Map<String, Object> map = new HashMap<>();
		List<MemoDTO> list = memoService.getMemoList();
		map.put("items", list);
		return new ModelAndView("memo/memo_list", "map", map);
	}
	
}
