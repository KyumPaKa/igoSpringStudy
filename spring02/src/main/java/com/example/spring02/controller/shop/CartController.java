package com.example.spring02.controller.shop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.shop.dto.CartDTO;
import com.example.spring02.service.shop.CartService;

@Controller
@RequestMapping("/shop/cart/*") // 공통적인 url mapping
public class CartController {

	@Inject
	CartService cartService;
	
	@RequestMapping("insert.do") // 세부적인 url mapping
	public String insert(@ModelAttribute CartDTO dto, HttpSession session) {
		// 로그인 여부를 체크하기 위해 세션에 저장된 아이디 확인
		String userid=(String)session.getAttribute("userid");
		System.out.println(userid+"test용");
//		if(userid == null) {
//			return "redirect:/member/login.do";
//		}
		dto.setUserid(userid);
		cartService.insert(dto); // 장바구니 테이블에 저장됨
		return "redirect:/shop/cart/list.do"; // 장바구니 목록으로 이동
	}
	
	@RequestMapping("list.do")
	public ModelAndView list(HttpSession session, ModelAndView mav) {
		Map<String, Object> map = new HashMap<>();
		// 세션 변수
		String userid = (String) session.getAttribute("userid");
		if(userid != null) { // 로그인한 경우
			List<CartDTO> list = cartService.listCart(userid); // 장바구니 목록
			int sumMoney = cartService.sumMoney(userid); // 장바구니 금액 합계
			int fee = sumMoney >= 30000 ? 0 : 2500;	// 배송료 계산, 3만원 이상이면 배송료 면제
			map.put("sumMoney", sumMoney); // 금액 합계
			map.put("fee", fee); // 배송료
			map.put("sum", sumMoney + fee); // 전체 금액
			map.put("list", list); // 장바구니 목록
			map.put("count", list.size()); // 레코드 갯수
			mav.setViewName("shop/cart_list"); // 이동할 페이지의 이름
			mav.addObject("map", map); // 뷰에 전달할 데이터
			return mav; // cart_list.jsp로 포워딩
		} else { // 로그인하지 않은 경우
			//return new ModelAndView("member/login", "", null);
			return null;
		}
	}
	
	@RequestMapping("delete.do")
	public String delete(@RequestParam int cart_id) {
		cartService.delete(cart_id);
		return "redirect:/shop/cart/list.do";
	}
	
	@RequestMapping("deleteAll.do")
	public String deleteAll(HttpSession session) {
		String userid = (String)session.getAttribute("userid");
		if(userid != null) {
			cartService.deleteAll(userid);
		}
		return "redirect:/shop/cart/list.do";
	}
	
	@RequestMapping("update.do")
	public String update(@RequestParam int[] amount,@RequestParam int[] cart_id, HttpSession session) {
		String userid = (String)session.getAttribute("userid");
		for(int i = 0; i < cart_id.length; i++) {
			if(amount[i] == 0) {
				cartService.delete(cart_id[i]);
			} else {
				CartDTO dto = new CartDTO();
				dto.setUserid(userid);
				dto.setCart_id(cart_id[i]);
				dto.setAmount(amount[i]);
				cartService.modifyCart(dto);
			}
		}
		return "redirect:/shop/cart/list.do";
	}
	
}
