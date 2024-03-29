package com.example.spring02.controller.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.board.dto.BoardDTO;
import com.example.spring02.service.board.BoardService;
import com.example.spring02.service.board.Pager;

@Controller // controller bean
@RequestMapping("/board/*") // 공통적인 mapping
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject // 서비스 객체 주입
	BoardService boardService;
	
	@RequestMapping("list.do") // 세부적인 mapping
	public ModelAndView list(@RequestParam(defaultValue="1") int curPage, 
			@RequestParam(defaultValue="all") String search_option, @RequestParam(defaultValue="") String keyword) throws Exception {
		logger.debug("search_option:" + search_option);
		logger.debug("keyword:" + keyword);
		
		// 레코드 갯수 계산
		int count = boardService.countArticle(search_option, keyword);
		// 페이지 나누기 관련 처리
		Pager pager = new Pager(count, curPage);
		int start = pager.getPageBegin();
		int end = pager.getPageEnd();
		List<BoardDTO> list = boardService.listAll(start, end, search_option, keyword);
		// 모델과 뷰
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/list"); // 뷰를 list.jsp로 설정
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list); // 맵에 자료 저장
		map.put("count", count);
		map.put("search_option", search_option);
		map.put("keyword", keyword);
		map.put("pager", pager); 
		mav.addObject("map", map); // 데이터 저장
		return mav; // list.jsp로 List가 전달됨
	}
	
	@RequestMapping("write.do")
	public String write() {
		return "board/write";
	}
	
	@RequestMapping("delete.do")
	public String delete(int bno) throws Exception {
		boardService.delete(bno);
		return "redirect:/board/list.do";
	}

	@RequestMapping("insert.do")
	public String insert(@ModelAttribute BoardDTO dto, HttpSession session) throws Exception {
		// 로그인한 사용자의 아이디
		String writer = (String)session.getAttribute("userid");
		dto.setWriter(writer);
		// 레코드가 저장됨
		boardService.create(dto);
		// 목록 갱신
		return "redirect:/board/list.do";
	}
	
	@RequestMapping(value="view.do", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam int bno, @RequestParam int curPage, 
			@RequestParam String search_option, @RequestParam String keyword, HttpSession session) throws Exception {
		boardService.increateViewcnt(bno);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/view");
		mav.addObject("dto", boardService.read(bno));
		mav.addObject("curPage", curPage);
		mav.addObject("search_option", search_option);
		mav.addObject("keyword", keyword);
		return mav;
	}
	
	@RequestMapping("update.do")
	public String update(BoardDTO dto) throws Exception {
		boardService.update(dto);
		return "redirect:/board/list.do";
	}
	
	@RequestMapping("getAttach/{bno}")
	@ResponseBody
	public List<String> getAttach(@PathVariable("bno") int bno){
		return boardService.getAttach(bno);
	}
	
}
