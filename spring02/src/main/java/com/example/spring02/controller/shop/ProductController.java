package com.example.spring02.controller.shop;

import java.io.File;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.shop.dto.ProductDTO;
import com.example.spring02.service.shop.ProductService;

@Controller // 현재 클래스를 스프링에서 관리하는 Controller Bean으로 설정
@RequestMapping("/shop/product/*") // 공통적인 url mapping
public class ProductController {

	@Inject // 의존관계 주입(DI)
	ProductService productService; // 스프링에서 만든 서비스 객체를 연결시킴
	
	@RequestMapping("/list.do") // 세부적인 url mapping
	public ModelAndView list(ModelAndView mav) {
		mav.setViewName("/shop/product_list"); // 이동할 페이지 이름
		mav.addObject("list", productService.listProduct()); // 데이터 저장
		return mav; // 페이지 이동
	}
	
	@RequestMapping("/detail.do/{product_id}")
	public ModelAndView detail(@PathVariable("product_id") int product_id, ModelAndView mav) {
		mav.setViewName("/shop/product_detail");
		mav.addObject("dto", productService.detailProduct(product_id));
		return mav;
	}
	
	@RequestMapping("write.do")
	public String write() {
		return "shop/product_write";
	}
	
	@RequestMapping("insert.do")
	public String insert(@ModelAttribute ProductDTO dto, HttpServletRequest request) {
		String filename="-";
		if(!dto.getFile1().isEmpty()) { // 첨부파일이 존재하면
			filename = dto.getFile1().getOriginalFilename();
			String path = request.getSession().getServletContext().getRealPath("/") + "WEB-INF\\views\\images\\";
			try {
				new File(path).mkdir();
				dto.getFile1().transferTo(new File(path+filename));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		dto.setPicture_url(filename);
		productService.insertProduct(dto);
		return "redirect:/shop/product/list.do";
	}
	
	@RequestMapping("edit/{product_id}")
	public ModelAndView edit(@PathVariable("product_id") int product_id, ModelAndView mav) {
		mav.setViewName("/shop/product_edit"); // 이동할 페이지 이름
		// 전달할 데이터를 저장
		mav.addObject("dto", productService.detailProduct(product_id));
		return mav; // 페이지 이동
	}
	
	@RequestMapping("update.do")
	public String update(@ModelAttribute ProductDTO dto, HttpServletRequest request) {
		String filename="-";
		if(!dto.getFile1().isEmpty()) { // 첨부파일이 존재하면
			filename = dto.getFile1().getOriginalFilename();
			String path = request.getSession().getServletContext().getRealPath("/") + "WEB-INF\\views\\images\\";
			try {
				new File(path).mkdir();
				dto.getFile1().transferTo(new File(path+filename));
			} catch (Exception e) {
				e.printStackTrace();
			}
			dto.setPicture_url(filename);
		} else {
			ProductDTO dto2 = productService.detailProduct(dto.getProduct_id());
			dto.setPicture_url(dto2.getPicture_url());
		}
		productService.updateProduct(dto);
		return "redirect:/shop/product/list.do";		
	}
	
	@RequestMapping("delete.do")
	public String delete(@RequestParam int product_id, HttpServletRequest request) {
		// 첨부파일의 이름
		String filename = productService.fileInfo(product_id);
		if(filename != null && !filename.contentEquals("-")) {
			String path = request.getSession().getServletContext().getRealPath("/") + "WEB-INF\\views\\images\\";
			File f = new File(path + filename);
			if(f.exists()) { // 파일이 존재하면
				f.delete(); // 파일 삭제
			}
		}
		productService.deleteProduct(product_id); // 레코드 삭제
		return "redirect:/shop/product/list.do";
	}
	
}
