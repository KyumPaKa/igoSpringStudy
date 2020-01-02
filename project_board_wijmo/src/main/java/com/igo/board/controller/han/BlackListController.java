package com.igo.board.controller.han;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.igo.board.model.cho.dto.BoardDTO;
import com.igo.board.model.han.blacklist.dto.BlackListDTO;
import com.igo.board.model.han.blacklist.dto.BoardUserDTO;
import com.igo.board.model.han.blacklist.dto.SanctionDTO;
import com.igo.board.service.han.AdminService;
import com.igo.board.service.han.Pager;

@Controller
@RequestMapping("admin/*")
public class BlackListController {

	@Inject
	AdminService adminService;
	
	@RequestMapping("blacklist.do")
	public ModelAndView list(@RequestParam(defaultValue="all") String search_option,@RequestParam(defaultValue="") String keyword,@RequestParam(defaultValue="1") int curPage)throws Exception {
		int count = adminService.countArticle(search_option,keyword);
		Pager pager = new Pager(count,curPage);
		int start = pager.getPageBegin();
		int end=pager.getPageEnd();
		List<BlackListDTO> list = adminService.listAll(start,end,search_option,keyword);
		ModelAndView mav = new ModelAndView();
		Map<String,Object> map = new HashMap<>();
		map.put("list",list);
		map.put("count",count);
		map.put("search_option",search_option);
		map.put("keyword",keyword);
		map.put("pager",pager);
		mav.setViewName("admin/blacklist");
		mav.addObject("map",map);
		return mav;
	}
	
	@RequestMapping("sanction.do")                                   //신고 내역
	public ModelAndView sanctionlist(@RequestParam(defaultValue="all") String search_option,@RequestParam(defaultValue="") String keyword,@RequestParam(defaultValue="1") int curPage)throws Exception {
		int count = adminService.countsanction(search_option,keyword);
		Pager pager = new Pager(count,curPage);
		int start = pager.getPageBegin();
		int end=pager.getPageEnd();
		List<SanctionDTO> list = adminService.sanctionList(start,end,search_option,keyword);
		ModelAndView mav = new ModelAndView();
		Map<String,Object> map = new HashMap<>();
		map.put("list",list);
		map.put("count",count);
		map.put("search_option",search_option);
		map.put("keyword",keyword);
		map.put("pager",pager);
		mav.setViewName("admin/sanctionlist");
		mav.addObject("map",map);
		return mav;
	}
	
	@RequestMapping("level.do")                                       //등급 관리
	public ModelAndView levelList(@RequestParam(defaultValue="all") String search_option,@RequestParam(defaultValue="") String keyword,@RequestParam(defaultValue="1") int curPage)throws Exception {
		int count = adminService.countmember(search_option,keyword);
		Pager pager = new Pager(count,curPage);
		int start = pager.getPageBegin();
		int end=pager.getPageEnd();
		List<BoardUserDTO> list = adminService.memberList(start,end,search_option,keyword);
		
		for(BoardUserDTO dto:list) {
			List<BoardUserDTO> listlist = adminService.countsum(dto.getUserid());
			for (BoardUserDTO dto2 : listlist) {
				dto.setBoard(dto2.getBoard());
				dto.setRply(dto2.getRply());
			}
		}
		ModelAndView mav = new ModelAndView();
		Map<String,Object> map = new HashMap<>();
		map.put("list",list);
		map.put("count",count);
		map.put("search_option",search_option);
		map.put("keyword",keyword);
		map.put("pager",pager);
		mav.setViewName("admin/level");
		mav.addObject("map",map);
		return mav;
	}

	@ResponseBody
	@RequestMapping(value="clearblacklist",method=RequestMethod.POST)
	public String clearBlackList(@RequestParam String refuserid) {
		adminService.clearBlackList(refuserid);
		return "admin/blacklist";
	}
	@ResponseBody
	@RequestMapping(value="updateblacklist",method=RequestMethod.POST)
	public String updateblacklist(@RequestParam String refuserid,@RequestParam String category) {
		int count = adminService.checkblack(refuserid);
		String rearr = "";
		if(count>0) {
			//사유 추가
			List<SanctionDTO> ctg = adminService.getreportreason(refuserid);
			for(SanctionDTO reason : ctg) {
				rearr += reason.getCategory();
			}
			adminService.updateblack(refuserid, rearr);
		}else {
			//새로운 블랙리스트
		adminService.updateblacklist(refuserid,category);
		}
		return "admin/sanction";
	}
	
	@ResponseBody
	@RequestMapping(value="changelevel",method=RequestMethod.POST)
	public String changeLevel(@RequestParam String refuserid,@RequestParam String authority) {
		adminService.changeLevel(refuserid,authority);
		//return "admin/level";
		return "redirect:/admin/level.do";
	}
	@ResponseBody
	@RequestMapping(value="clearsanction",method=RequestMethod.POST)
	public String clearSanction(@RequestParam String refuserid){
		adminService.clearSanction(refuserid);
		return "admin/sanction";
	}
	
	
	@ResponseBody
	@RequestMapping(value="report")
	public ModelAndView viewReport(@RequestParam String refidx,@RequestParam int idx,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Map<String,Object> map = new HashMap<>();
		switch(refidx) {
		case "1" : refidx="memo";break;
		case "2" : refidx="freeboard"; break;
		case "3" : refidx="unknownboard";break;
		case "4" : refidx="noticeboard";break;
		}
		map.put("refidx",refidx);
		map.put("idx",idx);
		BoardDTO list = adminService.reportView(map);
		mav.setViewName("user/report");
		mav.addObject("list",list);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value="reportrply")
	public ModelAndView rplyReport(@RequestParam String refidx,@RequestParam int idx,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Map<String,Object> map = new HashMap<>();
		switch(refidx) {
		case "1" : refidx="memo";break;
		case "2" : refidx="freeboard"; break;
		case "3" : refidx="unknownboard";break;
		case "4" : refidx="noticeboard";break;
		}
		map.put("refidx",refidx);
		map.put("idx",idx);
		BoardDTO list = adminService.rplyreportView(map);
		mav.setViewName("user/report");
		mav.addObject("list",list);
		return mav;
	}
	
	@RequestMapping("insertreport.do")
	public String insert(@ModelAttribute SanctionDTO san) {
		Map<String,Object> map = new HashMap<>();
		int refidx = san.getRefidx();
		map.put("refidx",refidx);
		int refboardcategory = san.getRefboardcategory();
		map.put("refboardcategory",refboardcategory);
		String category = san.getCategory();
		map.put("category", category);
		int count = adminService.reportcount(map);//글이 있는지 없는지
		if(count>0) {
			//이미 신고내역에 존재할 경우
		adminService.reportplus(map);	
		}else {
			//신규 신고일 경우
		adminService.insertreport(san);
		}
		return "";
	}
}

