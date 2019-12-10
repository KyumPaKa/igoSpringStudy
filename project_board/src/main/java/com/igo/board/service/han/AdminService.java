package com.igo.board.service.han;

import java.util.List;
import java.util.Map;

import com.igo.board.model.cho.dto.BoardDTO;
import com.igo.board.model.han.blacklist.dto.BlackListDTO;
import com.igo.board.model.han.blacklist.dto.BoardUserDTO;
import com.igo.board.model.han.blacklist.dto.SanctionDTO;

public interface AdminService {
	//게시글 리스트 갯수
	public int countArticle(String search_option,String keyword)throws Exception;
	//블랙리스트 
	public List<BlackListDTO> listAll(int start, int end, String search_option, String keyword);
	//신고내역리스트
	public List<SanctionDTO> sanctionList(int start, int end, String search_option, String keyword);
	//신고내역 리스트 갯수
	public int countsanction(String search_option, String keyword);
	//등급관리리스트
	public List<BoardUserDTO> memberList(int start, int end, String search_option, String keyword);
	public int countmember(String search_option, String keyword);
	//블랙리스트 복구
	public void clearBlackList(String refuserid);
	//관리자권한에서 회원 등급 관리
	public void changeLevel(String refuserid, String authority);
	//신고내역 삭제
	public void clearSanction(String refuserid);
	//신고게시글 가져오기
	public BoardDTO reportView(Map<String, Object> map);
	//신고창에서 신고하기(신고내역에 추가)
	public void insertreport(SanctionDTO san);
	//댓글신고
	public BoardDTO rplyreportView(Map<String, Object> map);
	//신고내역에서 블랙리스트 등록처리
	public void updateblacklist(String refuserid,String category);
	//신고 내역 확인
	public int reportcount(Map<String, Object> map);
	//신고 사유 추가
	public void reportplus(Map<String, Object> map);
	//블랙리스트 회원인지 확인
	public int checkblack(String refuserid);
	//블랙리스트에 이미 존재할 경우 사유추가
	public void updateblack(String refuserid, String category);
	//게시글,댓글 수
	public List<BoardUserDTO> countsum(String userid);
	//자동등업
	public void checkRole(String userid, String what);//신고내역에 있는 사유들 불러오기
	public List<SanctionDTO> getreportreason(String refuserid);	
	}
