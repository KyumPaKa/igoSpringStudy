package com.igo.board.controller.kim;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.igo.board.model.kim.dto.CategoryDTO;
import com.igo.board.service.kim.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@RequestMapping("/way.do")
	public String category(Model model) {
		List<CategoryDTO> cgList = categoryService.listSv();
		
		model.addAttribute("cglist", cgList);
		System.out.println(model.toString());
		
		return "/";
	}
	
	@RequestMapping("/list.do")
	public String list(
			Model model, 
			@RequestParam int idx, 
			@RequestParam(defaultValue="1") int curPage, 
			@RequestParam(defaultValue="all") String search_option, 
			@RequestParam(defaultValue="") String keyword) {
		
		List<CategoryDTO> list = categoryService.listSv();
		model.addAttribute("list", list);
		
		return "admin/cgSettring";
	}
	
}
