package com.igo.creditplus.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.igo.creditplus.model.dto.CompanyDTO;
import com.igo.creditplus.model.dto.EvalDTO;
import com.igo.creditplus.model.dto.HistoryDTO;

@Repository
public class CpDAOImpl implements CpDAO {

	@Resource
	SqlSession sqlSession;

	@Override
	public List<CompanyDTO> selectCompany(String empNo) {
		Map map = new HashMap();
		map.put("empno", empNo);
		return sqlSession.selectList("cp.selectCompany", map);
	}

	@Override
	public void ComHistorySave(HistoryDTO dto) {
		sqlSession.insert("cp.comHistorySave", dto);
	}

	@Override
	public List<HistoryDTO> selectComHistory(String empNo) {
		return sqlSession.selectList("cp.selectComHistory", empNo);
	}

	@Override
	public List<CompanyDTO> comSearchByName(String empName) {
		return sqlSession.selectList("cp.comSearchByName", empName);
	}

	@Override
	public List<EvalDTO> selectEval(EvalDTO dto) {
		return sqlSession.selectList("cp.selectEval", dto);
	}

	@Override
	public List<CompanyDTO> selectCreditCon(String empNo) {
		Map map = new HashMap();
		map.put("empno", empNo);
		return sqlSession.selectList("cp.selectCreditCon", map);
	}
	
}
