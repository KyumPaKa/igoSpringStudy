package com.example.security.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.security.model.dao.UserDAO;

@Controller // controller bean
public class UserController {

	@Inject
	BCryptPasswordEncoder passwordEncoder; // 비밀번호 암호화 객체
	
	@Inject
	private UserDAO userDao;
	
	@RequestMapping("/") // home
	public String home(Model model) {
		return "home";
	}
	
	@RequestMapping("/user/login.do") // 로그인 페이지로 이동
	public String login() {
		return "user/login";
	}
	
	@RequestMapping("/user/join.do") // 회원가입 페이지로 이동
	public String join() {
		return "user/join";
	}
	
	// 권한이 없는 사용자에게 안내 페이지 출력
	@RequestMapping("/user/denied")
	public String denied(Model model, Authentication auth, HttpServletRequest req) {
		AccessDeniedException ade = (AccessDeniedException)req.getAttribute(WebAttributes.ACCESS_DENIED_403);
		model.addAttribute("errMsg", ade);
		return "user/denied";
	}
	
	@RequestMapping("/admin/main.do") // 관리자 페이지로 이동
	public String admin() {
		return "admin/main";
	}
	
	// 회원가입 처리
	@RequestMapping("/user/insertUser.do")
	public String insertUser(@RequestParam String userid, @RequestParam String passwd, @RequestParam String name, @RequestParam String authority) {
		Map<String, String> map = new HashMap<>();
		map.put("userid", userid);
		System.out.println("암호화 전의 비번 : " + passwd);
		// 비밀번호 암호화
		String encryptPassword = passwordEncoder.encode(passwd);
		System.out.println("암호화 후의 비번 : " + encryptPassword);
		map.put("passwd", encryptPassword);
		map.put("name", name);
		map.put("authority", authority);
		userDao.insertUser(map);
		return "user/login";
	}
	
	@RequestMapping("/user/logout.do") // 로그아웃 처리
	public String logout(HttpSession session) {
		session.invalidate(); // 세션 클리어
		return "redirect:/"; // 시작 페이지로 이동
	}
	
}
