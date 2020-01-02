package com.igo.board.controller.cho;

import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.igo.board.model.cho.dto.ReplyDTO;
import com.igo.board.service.cho.Pager;
import com.igo.board.service.cho.ReplyService;
import com.igo.board.service.han.AdminService;

@Controller
@RequestMapping("/ubr")
public class UbReplyController {

	@Resource(name="ubReplyServiceImpl")
	ReplyService replyService;
	
	@Inject
	AdminService adminService;
	
	@RequestMapping("/list.do")
	public String list(@RequestParam int refIdx, @RequestParam(defaultValue="1") int curPage, Model model
			, HttpServletResponse response, HttpServletRequest request, @RequestParam(required = false) String category) {
		int count = replyService.count(refIdx);
		Pager pager = new Pager(count, curPage);
		int start = pager.getPageBegin();
		int end = pager.getPageEnd();
		List<ReplyDTO> list = replyService.list(refIdx, start, end);
		model.addAttribute("list", list);
		model.addAttribute("pager", pager);
		model.addAttribute("category", category);
		return "unknownBoard/replyList";
	}
	
	@RequestMapping("/write.do")
	@ResponseBody
	@Transactional
	public void write(@ModelAttribute ReplyDTO replyDTO, Principal principal) {
		replyService.write(replyDTO, principal);
		adminService.checkRole(principal.getName(), "reply");
	}
	
	@RequestMapping("/delete.do")
	@ResponseBody
	public void delete(@RequestParam int idx) {
		replyService.delete(idx);
	}
	
	@RequestMapping("/deleteAdmin.do")
	public String deleteAdmin(@RequestParam int idx, @RequestParam int refIdx, @RequestParam String category, Principal principal) {
		replyService.delete(idx);
		adminService.updateblacklist(principal.getName(), category);
		return "redirect:/admin/sanction.do";
	}
	
	@RequestMapping("/update.do")
	@ResponseBody
	public void update(@ModelAttribute ReplyDTO replyDTO) {
		replyService.update(replyDTO);
	}
	
}
