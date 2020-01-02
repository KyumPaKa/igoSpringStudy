package com.igo.board.model.han.blacklist.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.igo.board.model.cho.dto.BoardDTO;
import com.igo.board.model.han.blacklist.dto.BlackListDTO;
import com.igo.board.model.han.blacklist.dto.BoardUserDTO;
import com.igo.board.model.han.blacklist.dto.SanctionDTO;

@Repository
public class AdminDaoImpl implements AdminDao {

	@Inject
	SqlSession sqlSession;
	
	@Override
	public int countArticle(String search_option, String keyword) throws Exception {
		Map<String,String> map = new HashMap<>();
		map.put("search_option",search_option);
		map.put("keyword",keyword);
		return sqlSession.selectOne("admin.countArticle",map);
	}

	@Override
	public List<BlackListDTO> listAll(int start, int end, String search_option, String keyword) {
		Map<String,Object> map=new HashMap<>();
		map.put("search_option",search_option);
		map.put("keyword", keyword);
		map.put("start", start);
		map.put("end", end);
		return sqlSession.selectList("admin.listAll",map);
	}

	@Override
	public List<SanctionDTO> sanctionAll(int start, int end, String search_option, String keyword) {
		Map<String,Object> map=new HashMap<>();
		map.put("search_option",search_option);
		map.put("keyword", keyword);
		map.put("start", start);
		map.put("end", end);
		return sqlSession.selectList("admin.sanctionList",map);
	}

	@Override
	public int countsanction(String search_option, String keyword) {
		Map<String,String> map = new HashMap<>();
		map.put("search_option",search_option);
		map.put("keyword",keyword);
		return sqlSession.selectOne("admin.countsanction",map);
	}

	@Override
	public List<BoardUserDTO> memberList(int start, int end, String search_option, String keyword) {
		Map<String,Object> map=new HashMap<>();
		map.put("search_option",search_option);
		map.put("keyword", keyword);
		map.put("start", start);
		map.put("end", end);
		return sqlSession.selectList("admin.memberList",map);
	}

	@Override
	public int countmember(String search_option, String keyword) {
		Map<String,String> map = new HashMap<>();
		map.put("search_option",search_option);
		map.put("keyword",keyword);
		return sqlSession.selectOne("admin.countmember",map);
	}

	@Override
	public void clearBlackList(String refuserid) {
		sqlSession.update("admin.clearBlackList",refuserid);
	}

	@Override
	public void deleteBlackList(String refuserid) {
		sqlSession.delete("admin.deleteBlackList",refuserid);
	}

	@Override
	public void changeLevel(String refuserid, String authority) {
		Map<String,String> map = new HashMap<>();
		map.put("refuserid",refuserid);
		map.put("authority",authority);
		sqlSession.update("admin.changeLevel",map);
	}

	@Override
	public void clearSanction(String refuserid) {
		sqlSession.delete("admin.clearSanction",refuserid);
	}

	@Override
	public BoardDTO reportView(Map<String, Object> map) {
		return sqlSession.selectOne("admin.reportView", map);
	}

	@Override
	public void insertreport(SanctionDTO san) {
		sqlSession.insert("admin.insertreport",san);
	}

	@Override
	public BoardDTO rplyreportView(Map<String, Object> map) {
		return sqlSession.selectOne("admin.rplyreportView",map);
	}

	@Override
	public void updateblacklist(String refuserid) {
		sqlSession.update("admin.updateblacklist",refuserid);
	}

	@Override
	public int reportcount(Map<String, Object> map) {
		return sqlSession.selectOne("admin.reportcount",map);
	}

	@Override
	public void reportplus(Map<String, Object> map) {
		sqlSession.update("admin.reportplus",map);
	}

	@Override
	public void insertblacklist(String refuserid, String category) {
		Map<String,String> map = new HashMap<>();
		map.put("refuserid",refuserid);
		map.put("category",category);
		sqlSession.insert("admin.insertblacklist",map);
	}

	@Override
	public int checkblack(String refuserid) {						//기존의 블랙 멤버인지 확인
		return sqlSession.selectOne("admin.checkblack",refuserid);
	}

	@Override
	public void updateblack(String refuserid, String rns) {			//기존의 블랙리스트 사유 추가
		Map<String,String> map=new HashMap<>();
		map.put("refuserid",refuserid);
		map.put("category",rns);
		System.out.println(rns);
		sqlSession.update("admin.black",map);
	}

	@Override
	public String blackreason(String refuserid,String category) {
		Map<String,String> map = new HashMap<>();
		map.put("refuserid",refuserid);
		map.put("category",category);
		return sqlSession.selectOne("admin.blackreason",map);
	}

	@Override
	public void blackreg(String refuserid) {
		sqlSession.update("admin.blackreg",refuserid);
	}

	@Override
	public List<BoardUserDTO> countsun(String userid) {
		return sqlSession.selectList("admin.countsun",userid);
	}

	@Override
	public List<SanctionDTO> getreportreason(String refuserid) {
		return sqlSession.selectList("admin.getreportreason",refuserid);
	}

}
