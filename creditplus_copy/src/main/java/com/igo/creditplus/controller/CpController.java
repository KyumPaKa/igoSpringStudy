package com.igo.creditplus.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.igo.creditplus.model.dto.CompanyDTO;
import com.igo.creditplus.model.dto.EvalDTO;
import com.igo.creditplus.model.dto.HistoryDTO;
import com.igo.creditplus.service.CpService;

@Controller
public class CpController {
	
	@Resource
	CpService cpService;
	
	private static final Logger logger = LoggerFactory.getLogger(CpController.class);
	
	@RequestMapping(value = "/")
	public String home() {
		logger.info("HomeController home()");
		return "home";
	}
	
	@RequestMapping(value = "/creditTarget")
	public String creditTarget(Model model) {
		logger.info("HomeController creditTarget()");
		model.addAttribute("companyList", cpService.selectCompany("0"));
		return "creditTarget";
	}
	
	@RequestMapping(value = "/credit")
	public String credit(Model model, @RequestParam String empno) {
		logger.info("HomeController credit()");
		model.addAttribute("company", cpService.selectCreditCon(empno));
		return "credit";
	}
	
	@RequestMapping(value = "/comHistory")
	public String comHistory(@RequestParam String empno, Model model) {
		logger.info("HomeController comHistory()");
		model.addAttribute("history", cpService.selectComHistory(empno));
		model.addAttribute("company", cpService.selectCompany(empno));
		return "comHistory";
	}
	
	@RequestMapping(value = "/evalContent")
	public String evalContent(@RequestParam String empno, @RequestParam(defaultValue = "1") int accgroup, 
			@DateTimeFormat(pattern = "yy/MM/dd") Date evadate, Model model) {
		logger.info("HomeController evalContent()");
		EvalDTO dto = new EvalDTO();
		dto.setEmpno(empno);
		dto.setAccgroup(accgroup);
		dto.setEvadate(evadate);
		model.addAttribute("evalDTOList", cpService.selectEval(dto));
		model.addAttribute("empno", empno);
		model.addAttribute("history", cpService.selectComHistory(empno));
		model.addAttribute("company", cpService.selectCompany(empno));
		model.addAttribute("evadate", evadate);
		return "evalContent";
	}
	
	@ResponseBody
	@RequestMapping(value = "/comHistorySave")
	public void comHistorySave(@RequestBody List<HistoryDTO> dtoList) {
		logger.info("HomeController comHistorySave()");
		cpService.ComHistorySave(dtoList);
	}
	
	@RequestMapping(value = "/comSearch")
	public String comSearch(@RequestParam String empName, Model model) {
		logger.info("HomeController comSearch()");
		model.addAttribute("company", cpService.comSearchByName(empName));
		model.addAttribute("empName", empName);
		return "comSearch";
	}
	
	@ResponseBody
	@RequestMapping(value = "/comSearchByName")
	public List<CompanyDTO> comSearchByName(@RequestParam String empName) {
		logger.info("HomeController comSearchByName()");
		return cpService.comSearchByName(empName);
	}
	
	@ResponseBody
	@RequestMapping(value = "/evalcon")
	public List<EvalDTO> evalcon(@ModelAttribute EvalDTO dto, @DateTimeFormat(pattern = "yy/MM/dd") Date evadate) {
		logger.info("HomeController evalcon()");
		dto.setEvadate(evadate);
		return cpService.selectEval(dto);
	}
	
}
