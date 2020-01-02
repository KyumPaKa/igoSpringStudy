package com.igo.board.controller.woo;


import java.nio.file.AccessDeniedException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.igo.board.model.cho.dto.BoardDTO;
import com.igo.board.model.han.blacklist.dto.SanctionDTO;
import com.igo.board.model.woo.dao.UserDAO;
import com.igo.board.service.han.AdminService;
import com.igo.board.service.woo.UserService;

@Controller
public class UserController {
	@Inject
	BCryptPasswordEncoder passwordEncoder;
	
	@Inject
	UserService userservice;
	
	@Inject
	private UserDAO userDao;
	
	@RequestMapping("/")
	public String home(Locale locale, Model model) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		return "home";
	}

	@RequestMapping("/user/login.do")
	public String login() {
		return "user/login";
	}
	
	@RequestMapping("/user/join.do")
	public String join() {
		return "user/join";
	}

	@RequestMapping("/user/denied")
	public String denied(Model model, Authentication auth,
			HttpServletRequest req) {
		AccessDeniedException ade=
				(AccessDeniedException)req.getAttribute(
						WebAttributes.ACCESS_DENIED_403);
		model.addAttribute("errMsg", ade);
		return"user/denied";
	}
	
	@RequestMapping("/admin")
		public String listUser(Model model) {
		return "admin/main";
	}
	
	@RequestMapping("/user/address.do")
	public String address() {
		return "user/join";
	}
	
	@RequestMapping("/user/insertUser.do")
	public String insertUser(@RequestParam String userid,
			@RequestParam String passwd, @RequestParam String name,
			@RequestParam String email, @RequestParam String address1, @RequestParam String address2) {
		String address = address1 + address2;
		Map<String, String> map = new HashMap<>();
		map.put("userid", userid);
		System.out.println("암호화 전비번:"+passwd);
		String encryptPassword = passwordEncoder.encode(passwd);
		System.out.println("암호화 후비번:"+encryptPassword);
		map.put("passwd",  encryptPassword);
		map.put("name", name);
		map.put("email", email);
		map.put("address", address);
		System.out.println(email+address);
		userDao.insertUser(map);
		return "user/login";
	}

	
	@RequestMapping("/user/logout.do")
	public String logout(HttpSession session) {
		
	session.invalidate();
	return "redirect:/";
	}
	
	@RequestMapping("/user/idfind.do") 
	public String userFindIdPage() {
		return "/user/idfind"; 
	}
	
	@RequestMapping("/user/resultidfind.do") 
	public String resultidfind(@RequestParam String email, Model model) {
		System.out.println(email);
		String userid = userservice.resultid(email);
		System.out.println(userid);
		model.addAttribute("userid", userid);
		return "/user/resultidfind"; 
	}
	@RequestMapping("/user/pwfind.do") 
	public String userFindPwPage() {
		return "/user/pwfind"; 
	}

	@RequestMapping("/user/resultpwfind.do")
	public String resultpwfind(@RequestParam String userid, @RequestParam String email, Model model) {
		Map<String, String> map = new HashMap<>();
		map.put("userid", userid);
		map.put("email", email);
		String passwd = "0000"; 
		String encryptPasswordReset = passwordEncoder.encode(passwd);
		map.put("passwd", encryptPasswordReset);
		userDao.resultpwfind(map);
		model.addAttribute("userid", userid);
		return "/user/resultpwfind";
	}

	
	@RequestMapping("user/mypage.do")
	public String view(@RequestParam String userid, Model model) {
		Map<String, String> map = new HashMap<>();
		map.put("userid", userid);
		System.out.println(userid+":::아");
		String email = userservice.viewMember(userid);
		map.put("email", email);
		model.addAttribute("map", map);
		return "user/mypage";
	}
	
	@RequestMapping("user/update.do")
	public String update(@RequestParam String userid, @RequestParam String passwd) {
		Map<String, String> map = new HashMap<>();
		map.put("userid", userid);
		String modipasswd = passwd;
		String encryptPasswordReset = passwordEncoder.encode(modipasswd);
		map.put("passwd", encryptPasswordReset);
		userDao.update(map);
		return "user/login"; 
	}
	@Inject 
	AdminService adminService;
	
	@ResponseBody
	@RequestMapping(value="user/report")
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
		System.out.println(refidx);
		BoardDTO list = adminService.reportView(map);
		mav.setViewName("user/report");
		mav.addObject("list",list);
		return mav;
	}
	
	@RequestMapping("user/insertreport.do")
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
	
	@ResponseBody
	@RequestMapping(value="user/reportrply")
	public ModelAndView rplyReport(@RequestParam String refidx,@RequestParam int idx,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Map<String,Object> map = new HashMap<>();
		switch(refidx) {
		case "1" : refidx="memo";break;
		case "2" : refidx="freeboard"; break;
		case "3" : refidx="unknownboard";break;
		case "4" : refidx="noticeboard";break;
		}
		System.out.println(idx);
		System.out.println(refidx);
		map.put("refidx",refidx);
		map.put("idx",idx);
		BoardDTO list = adminService.rplyreportView(map);
		mav.setViewName("user/report");
		mav.addObject("list",list);
		System.out.println(list);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "user/idcheck.do", method = RequestMethod.POST)
		public int postIdCheck(HttpServletRequest req) throws Exception {
			String userid = req.getParameter("userid");
			System.out.println(userid);
			int idcheck = userservice.idcheck(userid);
			System.out.println(idcheck);
			int result = 0;
			
			if(idcheck != 0) {
				result = 1;
			}
		return result;
	}			
		@ResponseBody
		@RequestMapping(value = "user/emailcheck.do", method = RequestMethod.POST)
			public int postemailCheck(HttpServletRequest req) throws Exception {
				String email = req.getParameter("email");
				System.out.println(email+"dddd");
				int emailcheck = userservice.emailcheck(email);
				System.out.println(emailcheck);
				int result = 0;
				
				if(emailcheck != 0) {
					result = 1;
				}
			return result;
		}	
	
}
