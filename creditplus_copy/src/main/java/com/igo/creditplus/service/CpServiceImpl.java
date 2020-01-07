package com.igo.creditplus.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.igo.creditplus.model.dao.CpDAO;
import com.igo.creditplus.model.dto.CompanyDTO;
import com.igo.creditplus.model.dto.EvalDTO;
import com.igo.creditplus.model.dto.HistoryDTO;

@Service
public class CpServiceImpl implements CpService {

	@Resource
	CpDAO cpDao;

	@Override
	public List<CompanyDTO> selectCompany(String empNo) {
		return cpDao.selectCompany(empNo);
	}

	@Override
	public void ComHistorySave(List<HistoryDTO> dtoList) {
		for(int i = 0; i < dtoList.size(); i++) { 
			cpDao.ComHistorySave(dtoList.get(i));
		}
	}

	@Override
	public List<HistoryDTO> selectComHistory(String empNo) {
		return cpDao.selectComHistory(empNo);
	}

	@Override
	public List<CompanyDTO> comSearchByName(String empName) {
		return cpDao.comSearchByName(empName);
	}

	@Override
	public List<EvalDTO> selectEval(EvalDTO dto) {
		return cpDao.selectEval(dto);
	}

	@Override
	public List<CompanyDTO> selectCreditCon(String empNo) {
		return cpDao.selectCreditCon(empNo);
	}
	
}
