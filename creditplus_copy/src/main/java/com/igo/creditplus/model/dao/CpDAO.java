package com.igo.creditplus.model.dao;

import java.util.List;

import com.igo.creditplus.model.dto.CompanyDTO;
import com.igo.creditplus.model.dto.EvalDTO;
import com.igo.creditplus.model.dto.HistoryDTO;

public interface CpDAO {

	public List<CompanyDTO> selectCompany(String empNo);
	public void ComHistorySave(HistoryDTO dto);
	public List<HistoryDTO> selectComHistory(String empNo);
	public List<CompanyDTO> comSearchByName(String empName);
	public List<EvalDTO> selectEval(EvalDTO dto);
	public List<CompanyDTO> selectCreditCon(String empNo);
	
}
