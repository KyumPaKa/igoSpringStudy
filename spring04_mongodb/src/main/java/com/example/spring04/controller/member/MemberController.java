package com.example.spring04.controller.member;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring04.model.member.dto.MemberDTO;
import com.example.spring04.service.member.MemberService;

@Controller
@RequestMapping("/member/*") // 공통적인 mapping
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping("login.do") // 세부적인 mapping
	public ModelAndView login() {
		return new ModelAndView("member/login");
	}
	
	@RequestMapping("join.do")
	public ModelAndView join() {
		// 회원가입 페이지로 이동
		return new ModelAndView("member/join");
	}
	
	@RequestMapping("member_join.do")
	public String member_join(String _id, String passwd, String name) throws Exception {
		// MongoDB에 저장됨
		MemberDTO dto = new MemberDTO();
		dto.set_id(_id);
		dto.setPasswd(passwd);
		dto.setName(name);
		memberService.join(dto);
		// 로그인 페이지로 이동
		return "redirect:/member/login.do";
	}
	
	@RequestMapping("login_check.do")
	public ModelAndView login_check(String _id, String passwd, HttpSession session) throws Exception {
		MemberDTO dto = memberService.loginCheck(_id, passwd);
		String message = "";
		String url = "";
		if(dto == null) {
			message = "로그인 정보가 정확하지 않습니다.";
			url = "member/login";
		} else {
			message = dto.getName() + "님 환영합니다.";
			url = "home";
			session.setAttribute("userid", _id);
			session.setAttribute("name", dto.getName());
		}
		Map<String, Object> map = new HashMap<>();
		map.put("message", message);
		return new ModelAndView(url, "map", map);
	}

	@RequestMapping("logout.do")
	public String logout(HttpSession session) throws Exception {
		session.invalidate();
		return "redirect:/member/login.do";
	}
	
}
