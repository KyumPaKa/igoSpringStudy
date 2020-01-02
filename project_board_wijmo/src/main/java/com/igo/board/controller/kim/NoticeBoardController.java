package com.igo.board.controller.kim;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.igo.board.model.cho.dto.BoardDTO;
import com.igo.board.model.kim.dto.NoticeDTO;
import com.igo.board.model.woo.dto.UserDTO;
import com.igo.board.service.cho.Pager;
import com.igo.board.service.kim.NoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeBoardController {

	@Autowired
	NoticeService noticeService;
	
	@RequestMapping("/list.do")
	public String list(Model model, @RequestParam int refIdx, @RequestParam(defaultValue="1") int curPage, 
			@RequestParam(defaultValue="all") String search_option, @RequestParam(defaultValue="") String keyword) {
		
//		List<NoticeDTO> list = noticeService.listSv();
		List<NoticeDTO> list = noticeService.selectedListSv(refIdx);
		
		System.out.println(list.toString());
		
		model.addAttribute("list", list);
		
		return "notice/list";
		
	}
	
//    @RequestMapping("view.do")
//    public ModelAndView view(
//    		@RequestParam int bno,
//    		@RequestParam int curPage,
//    		@RequestParam String search_option,
//    		@RequestParam String keyword,
//    		HttpSession session) throws Exception {
    
	@RequestMapping("view.do")
    public ModelAndView view(
    		@RequestParam int idx,
    		HttpSession session) throws Exception {
        
        ModelAndView mav=new ModelAndView();
        mav.setViewName("notice/view"); //포워딩할 뷰의 이름
        mav.addObject("list", noticeService.selectedSv(idx)); //자료 저장
        noticeService.noticeVcSv(idx);
        
        return mav; //  views/board/view.jsp로 넘어가서 출력됨
    }
	
	@RequestMapping("write.do")
	public String write() {
		return "notice/write";
	}
    
    @RequestMapping("insert.do")
    public String insert(@ModelAttribute NoticeDTO dto, Principal principal, HttpSession session, HttpServletRequest request) throws Exception {
    	dto.setRefwriter(principal.getName());
    	System.out.println("dto.toString(): " + dto.toString());
    	dto.setRefpoint(3);

    	// 게시판 입력
    	noticeService.noticeInsertSv(dto);
    	
    	List<NoticeDTO> list = noticeService.noticeGetIdx();
    	for(Object vo : list){

    		NoticeDTO NoticeDto = (NoticeDTO)vo;
    		System.out.println("NoticeDto.getIdx1(): " + NoticeDto.getIdx());
    		dto.setIdx(NoticeDto.getIdx());
//    		if(!NoticeDto.getIdx().isEmpty()){
//    			System.out.println("");
//    		}
    	}
    	
    	try {
//    		int refidx1 = Integer.parseInt(request.getParameter("refidx"));
    		String[] refidx1 = request.getParameterValues("refidx");
    		int[] refidxNum = new int[refidx1.length];
    		for(int i=0;i<refidx1.length; i++) { 
    			refidxNum[i] = Integer.parseInt(refidx1[i]); 
    			System.out.println("refidxNum[i]1: " + refidxNum[i]); 
    			dto.setRefidx(refidxNum[i]);
    			noticeService.noticePtInsertSv(dto);
    		}
    		for(int i=0;i<refidx1.length; i++) { Integer.parseInt(refidx1[i]); System.out.println("refidxNum[i]2: " + Integer.parseInt(refidx1[i])); }
//            for(int j = 1; j <= refidx2 ; j++ ){
//    			for(int i = 0; i < request.getParameterValues("ch" + String.valueOf(j)).length ; i++){
//    				System.out.println(request.getParameterValues("ch" + String.valueOf(j))[i]);
//    			}
//    		}	
    	} catch(Exception e){e.getStackTrace();}
    	
//    	return "redirect:/notice/list.do";
    	return "home";
    }
    
	@RequestMapping("updateForm.do")
	public ModelAndView edit(@ModelAttribute NoticeDTO dto, @RequestParam int idx, ModelAndView mav) {
		
		List<NoticeDTO> list = noticeService.viewSv(idx);
//		System.out.println("list.toString()1: " + list.toString());
		
		int[] checkedCt = new int[list.size() + 1];
		
		int seq = 0;
    	for(Object vo : list){
    		
    		NoticeDTO NoticeDto = (NoticeDTO)vo;
    		checkedCt[seq] = NoticeDto.getRefidx(); 
    		System.out.println("NoticeDto.getIdx(): " + NoticeDto.getRefidx());
//    		if(!NoticeDto.getIdx().isEmpty()){
//    			System.out.println("");
//    		}
    		++seq;
    	}
    	
    	List<int[]> checkedList = Arrays.asList(checkedCt); 

    	mav.setViewName("/notice/updateForm");
		mav.addObject("checkedList", list);
		mav.addObject("list", noticeService.selectedSv(idx));
		System.out.println("list.toString()2: " + noticeService.selectedSv(idx));

		return mav;
	}
    
    @RequestMapping("update.do")
    public String update(@ModelAttribute NoticeDTO dto, HttpServletRequest request) throws Exception {
    	System.out.println("dto: " + dto);
    	noticeService.noticePtDeleteSv(dto.getIdx());
    	
    	if (dto != null) {
    		noticeService.noticeUpdateSv(dto);
    	}
    	
    	List<NoticeDTO> list = noticeService.noticeGetIdx();
    	for(Object vo : list){

    		NoticeDTO NoticeDto = (NoticeDTO)vo;
    		System.out.println("NoticeDto.getIdx1(): " + NoticeDto.getIdx());
    		dto.setIdx(NoticeDto.getIdx());
//    		if(!NoticeDto.getIdx().isEmpty()){
//    			System.out.println("");
//    		}
    	}
    	
    	try {
    		String[] refidx1 = request.getParameterValues("refidx");
    		int[] refidxNum = new int[refidx1.length];
    		for(int i=0;i<refidx1.length; i++) { 
    			refidxNum[i] = Integer.parseInt(refidx1[i]); 
    			System.out.println("refidxNum[i]1: " + refidxNum[i]); 
    			dto.setRefidx(refidxNum[i]);
    			noticeService.noticePtInsertSv(dto);
    		}
    	} catch(Exception e){e.getStackTrace();}
    	
    	return "redirect:/notice/view.do?idx=" + dto.getIdx();
    }
    
    @RequestMapping("delete.do")
    public String update(
    		@RequestParam int idx,
    		HttpSession session) throws Exception {
    	
    		noticeService.noticeDeleteSv(idx);
    		noticeService.noticePtDeleteSv(idx);
    		
    		return "home";
    }
}
