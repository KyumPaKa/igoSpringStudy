package com.igo.creditplus.service;

import java.util.List;

import com.igo.creditplus.model.dto.CompanyDTO;
import com.igo.creditplus.model.dto.EvalDTO;
import com.igo.creditplus.model.dto.HistoryDTO;

public interface CpService {

	public List<CompanyDTO> selectCompany(String empNo);
	public void ComHistorySave(List<HistoryDTO> dtoList);
	public List<HistoryDTO> selectComHistory(String empNo);
	public List<CompanyDTO> comSearchByName(String empName);
	public List<EvalDTO> selectEval(EvalDTO dto);
	public List<CompanyDTO> selectCreditCon(String empNo);
	
}
