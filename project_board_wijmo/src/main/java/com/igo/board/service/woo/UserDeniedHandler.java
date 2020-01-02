package com.igo.board.service.woo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class UserDeniedHandler implements AccessDeniedHandler{

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) 
					throws IOException, ServletException {
		request.setAttribute("errMsg", "블랙리스트는 접근할 수 없습니다. 문의 시 건의사항 게시판을 이용해주세요.");
		request.getRequestDispatcher("/WEB-INF/views/user/denied.jsp").forward(request, response);
		
	}

}
