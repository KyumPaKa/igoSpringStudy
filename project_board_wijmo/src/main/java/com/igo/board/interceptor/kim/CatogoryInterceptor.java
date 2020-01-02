package com.igo.board.interceptor.kim;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.igo.board.model.kim.dto.CategoryDTO;
import com.igo.board.service.kim.CategoryService;

public class CatogoryInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	CategoryService categoryService;
	
	@Override
	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler) throws Exception {
		
		System.out.println("preHandle CatogoryInterceptor");
//		HttpSession session = request.getSession();
//		
//		List<CategoryDTO> list = categoryService.listSv();
//		System.out.println(list.toString());
//		session.setAttribute("cgList", list);
//		
//		String requestUrl = request.getRequestURL().toString();
//		if(requestUrl.contains("/login.do")){
//			return true;
//		}
//		
//		if (session.getAttribute("cgidx") == null) {
//			response.sendRedirect(request.getContextPath() + "/user/login.do");
//			System.out.println("postHandle2");
//			return false;
//		}
		return true;
		
	}
	
	@Override
	public void postHandle(HttpServletRequest request, 
							HttpServletResponse response, 
							Object handler, 
							org.springframework.web.servlet.ModelAndView modelAndView) throws Exception {
		
//		System.out.println("postHandle1");
//		HttpSession session = request.getSession();
//		
//		List<CategoryDTO> list = categoryService.listSv();
//		Object cgList = modelAndView.addObject("cgList", list);
//		System.out.println(list.toString());
//		System.out.println("인터셉터");
//		if(list != null){
//		    System.out.println("cgList exists");
//		    request.setAttribute("cgList", list);
//		    request.getSession().setAttribute("cgList", cgList);
//		    
//		    String requestUrl = request.getRequestURL().toString();
//			if(requestUrl.contains("/login.do")){
//				response.sendRedirect("login.do");
//			}
//		}
		
	};
}
