package com.igo.board.filter.kim;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igo.board.model.kim.dto.CategoryDTO;
import com.igo.board.service.kim.CategoryService;

@Service("CategoryFilter")
public class springSecurityFilterChain implements Filter {
	
	@Autowired
	CategoryService categoryService;
	
	private static final Logger logger = LoggerFactory.getLogger(springSecurityFilterChain.class); 
	private String encoding;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	}
	
//	public void init(springSecurityFilterChain config) throws ServletException { 
//		logger.info("init call"); 
//		// web.xml 에서 필터 등록시 정의했던 파라미터를 가져옵니다. 
//	}
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		
//		List<CategoryDTO> list = categoryService.listSv();
//		System.out.println("doFilter CategoryFilter");
//		
//		if(list != null) {
//		    System.out.println("cgList exists");
//		    request.setAttribute("cgList", list);
//		    chain.doFilter(request, response);
//		}
//	}
//	
//	public void destroy() { 
//		logger.info("destroy call"); 
//	}


}
