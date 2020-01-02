package com.igo.board.model.han.blacklist.dao;

import java.util.List;
import java.util.Map;

import com.igo.board.model.cho.dto.BoardDTO;
import com.igo.board.model.han.blacklist.dto.BlackListDTO;
import com.igo.board.model.han.blacklist.dto.BoardUserDTO;
import com.igo.board.model.han.blacklist.dto.SanctionDTO;

public interface AdminDao {
	public int countArticle(String search_option,String keyword)throws Exception;

	public List<BlackListDTO> listAll(int start, int end, String search_option, String keyword);

	public List<SanctionDTO> sanctionAll(int start, int end, String search_option, String keyword);

	public int countsanction(String search_option, String keyword);

	public List<BoardUserDTO> memberList(int start, int end, String search_option, String keyword);

	public int countmember(String search_option, String keyword);

	public void clearBlackList(String refuserid);

	public void deleteBlackList(String refuserid);

	public void changeLevel(String refuserid, String authority);

	public void clearSanction(String refuserid);

	public BoardDTO reportView(Map<String, Object> map);

	public void insertreport(SanctionDTO san);

	public BoardDTO rplyreportView(Map<String, Object> map);

	public void updateblacklist(String refuserid);

	public int reportcount(Map<String, Object> map);

	public void reportplus(Map<String, Object> map);

	public void insertblacklist(String refuserid, String category);

	public int checkblack(String refuserid);
	
	public void updateblack(String refuserid, String rns);

	public String blackreason(String refuserid, String category);

	//블랙리스트 테이블에 있는 회원 권한 바꾸기
	public void blackreg(String refuserid);

	public List<BoardUserDTO> countsun(String userid);

	public List<SanctionDTO> getreportreason(String refuserid);
}
