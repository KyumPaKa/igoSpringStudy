package com.igo.board.controller.cho.suggestion;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.igo.board.model.cho.dto.suggestion.SuggestionDTO;
import com.igo.board.service.cho.suggestion.Pager;
import com.igo.board.service.cho.suggestion.SuggestionService;

@Controller
@RequestMapping("/suggestion/*")
public class SuggestionController {

	@Autowired
	SuggestionService suggestionService;
	
	@RequestMapping("/list.do")
	public String list(Model model, @RequestParam(defaultValue="1") int curPage, 
			@RequestParam(defaultValue="all") String search_option, @RequestParam(defaultValue="") String keyword) {
		// 레코드 갯수 계산
		int count = suggestionService.countArticle(search_option, keyword);
		// 페이지 나누기 관련 처리
		Pager pager = new Pager(count, curPage);
		int start = pager.getPageBegin();
		int end = pager.getPageEnd();
		List<SuggestionDTO> list = suggestionService.list(start, end, search_option, keyword);
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("search_option", search_option);
		model.addAttribute("keyword", keyword);
		model.addAttribute("pager", pager);
		return "suggestion/list";
	}
	
	@RequestMapping("/writeForm.do")
	public String writeForm() {
		return "suggestion/writeForm";
	}
	
	@RequestMapping("/write.do")
	public String write(@ModelAttribute SuggestionDTO suggestionDTO, MultipartHttpServletRequest mRequest) {
		String fileName = suggestionService.imgUpload(mRequest);
		suggestionDTO.setSg_file(fileName);
		suggestionDTO.setSg_writer("테스트");
		suggestionService.write(suggestionDTO);
		return "redirect:/suggestion/list.do";
	}
	
	@RequestMapping("/delete.do")
	public String delete(@RequestParam int sg_idx) {
		suggestionService.delete(sg_idx);
		return "redirect:/suggestion/list.do";
	}
	
	@RequestMapping("/updateForm.do")
	public String updateForm(@RequestParam int sg_idx, Model model) {
		model.addAttribute("suggestionDTO", suggestionService.updateForm(sg_idx));
		return "suggestion/updateForm";
	}
	
	@RequestMapping("/update.do")
	public String update(@ModelAttribute SuggestionDTO suggestionDTO, MultipartHttpServletRequest mRequest) {
		String fileName = suggestionService.imgUpload(mRequest);
		suggestionDTO.setSg_file(fileName);
		suggestionService.update(suggestionDTO);
		return "redirect:/suggestion/list.do";
	}
	
	@RequestMapping("/view.do")
	public String view(@RequestParam int sg_idx, Model model, HttpServletResponse response, HttpServletRequest request) {
		model.addAttribute("suggestionDTO", suggestionService.view(sg_idx, response, request));
		return "suggestion/view";
	}
	
	@ResponseBody
	@RequestMapping("/imageUpload.do")
	public void imageUpload(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest mRequest) throws Exception {
		// http header 설정
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String fileName = suggestionService.imgUpload(mRequest);
		
		String callback = request.getParameter("CKEditorFuncNum");
		String fileUrl = request.getContextPath() + "/resources/suggestion/images/" + fileName;
		
		PrintWriter printwriter = response.getWriter();
		printwriter.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + fileUrl + "','이미지를 업로드 하였습니다.')</script>");
		printwriter.flush();
        if (printwriter != null) {
            printwriter.close();
        }
	}
	
	@ResponseBody
	@RequestMapping("/increaseGood.do")
	public int increaseGood(@RequestParam int sg_idx, HttpServletRequest request) {
		return suggestionService.increaseGood(sg_idx, request);
	}
	
	@ResponseBody
	@RequestMapping("/increaseBad.do")
	public int increaseBad(@RequestParam int sg_idx, HttpServletRequest request) {
		return suggestionService.increaseBad(sg_idx, request);
	}
	 
}
