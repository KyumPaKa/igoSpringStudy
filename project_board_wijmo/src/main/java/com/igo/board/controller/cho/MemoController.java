package com.igo.board.controller.cho;

import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.igo.board.model.cho.dto.BoardDTO;
import com.igo.board.service.cho.BoardService;
import com.igo.board.service.cho.Pager;
import com.igo.board.service.han.AdminService;

@Controller
@RequestMapping("/mm")
public class MemoController {

	@Resource(name="memoServiceImpl")
	BoardService boardService;
	
	@Inject
	AdminService adminService;
		
	@RequestMapping("/list.do")
	public String view(@RequestParam int refIdx, Model model) {
		model.addAttribute("refIdx", refIdx);
		return "memo/list";
	}
	
	@RequestMapping("/view.do")
	public String list(Model model, @RequestParam int refIdx, @RequestParam(defaultValue="1") int curPage, @RequestParam(required = false) String category) {
		// 레코드 갯수 계산
		int count = boardService.countArticle("", "", refIdx);
		// 페이지 나누기 관련 처리
		Pager pager = new Pager(count, curPage);
		int start = pager.getPageBegin();
		int end = pager.getPageEnd();
		List<BoardDTO> list = boardService.list(start, end, "", "", refIdx);
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("pager", pager);
		model.addAttribute("refIdx", refIdx);
		model.addAttribute("category", category);
		return "memo/view";
	}
	
	@ResponseBody
	@RequestMapping("/good.do")
	public int good(@RequestParam int idx, Principal principal) {
		return boardService.good(idx, principal);
	}
	
	@ResponseBody
	@RequestMapping("/bad.do")
	public int bad(@RequestParam int idx, Principal principal) {
		return boardService.bad(idx, principal);
	}
	
	@RequestMapping("/delete.do")
	@ResponseBody
	public void delete(@RequestParam int idx) {
		boardService.delete(idx);
	}
	
	@RequestMapping("/deleteAdmin.do")
	public String deleteAdmin(@RequestParam int idx, @RequestParam int refIdx, @RequestParam String category, Principal principal) {
		boardService.delete(idx);
		adminService.updateblacklist(principal.getName(), category);
		return "redirect:/admin/sanction.do";
	}
	
	@RequestMapping("/write.do")
	@ResponseBody
	@Transactional
	public void write(@ModelAttribute BoardDTO boardDTO, Principal principal) {
		boardDTO.setRefWriter(principal.getName());
		boardService.write(boardDTO);
		adminService.checkRole(principal.getName(), "board");
	}
	
	@RequestMapping("/update.do")
	@ResponseBody
	public void update(@ModelAttribute BoardDTO boardDTO) {
		boardService.update(boardDTO);
	}
	
}
