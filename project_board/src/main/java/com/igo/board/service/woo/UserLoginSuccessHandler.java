package com.igo.board.service.woo;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.igo.board.model.woo.dto.UserDTO;

public class UserLoginSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) 
					throws IOException, ServletException {
		UserDTO dto=(UserDTO)authentication.getPrincipal();
		System.out.println(dto);
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = null;
		while (iter.hasNext()) {
			auth = iter.next();
		}
		String author = auth.getAuthority();
		System.out.println(author);
		if(author.equals("ROLE_BABY")) {
			author = "준회원";
		} else if (author.equals("ROLE_MEMBER")) {
				author = "정회원";
		} else if (author.equals("ROLE_MANAGER")) {
			 author = "매니저";
		} else if (author.equals("ROLE_ADMIN")) {
			 author = "관리자";
		} else if (author.equals("ROLE_BLACK")) {
			 author = "블랙리스트회원";
		}
	
		
		String msg=authentication.getName()+"("+ author +")"+"님 안녕하세요.";
		request.setAttribute("msg", msg);
		RequestDispatcher rd=request.getRequestDispatcher("/");
		rd.forward(request, response);
	}

}
