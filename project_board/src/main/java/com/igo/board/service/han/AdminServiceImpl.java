package com.igo.board.service.han;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.igo.board.model.cho.dto.BoardDTO;
import com.igo.board.model.han.blacklist.dao.AdminDao;
import com.igo.board.model.han.blacklist.dto.BlackListDTO;
import com.igo.board.model.han.blacklist.dto.BoardUserDTO;
import com.igo.board.model.han.blacklist.dto.SanctionDTO;
import com.igo.board.model.woo.dao.UserDAO;

@Service
public class AdminServiceImpl implements AdminService {

	@Inject
	AdminDao adminDao;

	@Inject
	UserDAO userDao;
	
	public int countArticle(String search_option, String keyword) throws Exception {
		return adminDao.countArticle(search_option, keyword);
	}

	@Override
	public List<BlackListDTO> listAll(int start, int end, String search_option, String keyword) {
		return adminDao.listAll(start, end, search_option, keyword);
	}

	@Override
	public List<SanctionDTO> sanctionList(int start, int end, String search_option, String keyword) {
		return adminDao.sanctionAll(start, end, search_option, keyword);
	}

	@Override
	public int countsanction(String search_option, String keyword) {
		return adminDao.countsanction(search_option, keyword);
	}

	@Override
	public List<BoardUserDTO> memberList(int start, int end, String search_option, String keyword) {
		return adminDao.memberList(start, end, search_option, keyword);
	}

	@Override
	public int countmember(String search_option, String keyword) {
		return adminDao.countmember(search_option, keyword);
	}

	@Override
	@Transactional
	public void clearBlackList(String refuserid) {
		adminDao.clearBlackList(refuserid);
		adminDao.deleteBlackList(refuserid);
	}

	@Override
	public void changeLevel(String refuserid, String authority) {
		adminDao.changeLevel(refuserid, authority);
	}

	@Override
	public void clearSanction(String refuserid) {
		adminDao.clearSanction(refuserid);
	}

	@Override
	public BoardDTO reportView(Map<String, Object> map) {
		return adminDao.reportView(map);
	}

	@Override
	public void insertreport(SanctionDTO san) {
		adminDao.insertreport(san);
	}

	@Override
	public BoardDTO rplyreportView(Map<String, Object> map) {
		return adminDao.rplyreportView(map);
	}

	@Transactional
	@Override // 신규블랙리스트
	public void updateblacklist(String refuserid, String category) {
		// sanction 처리완료로 상태변경
		adminDao.updateblacklist(refuserid);
		// boarduser 권한 블랙으로 등록
		adminDao.blackreg(refuserid);
		// blacklist table 에 insert
		adminDao.insertblacklist(refuserid, category);
	}

	@Override
	public int reportcount(Map<String, Object> map) {
		return adminDao.reportcount(map);
	}

	@Override
	public void reportplus(Map<String, Object> map) {
		adminDao.reportplus(map);
	}

	@Override
	public int checkblack(String refuserid) {
		return adminDao.checkblack(refuserid);

	}

	@Transactional
	@Override // 기존 블랙리스트
	public void updateblack(String refuserid, String category) {
		// sanction 처리완료로 상태 변경
		adminDao.updateblacklist(refuserid);
		// 기존의 사유카테고리 가져옴
		String reason = adminDao.blackreason(refuserid, category);
//		boolean a = false;
		
//		for (int i = 0; i < reason.length(); i++) {
//			if (reason.charAt(i) == ',') {
//				a = true;
//			}
//		}
//		
//		if (a == true) {
//			for (int i = 0; i < reason.split(",").length; i++) {
//				System.out.println(i+"번쨰"+reason.split(",")[i]);
//				if (reason.split(",")[i].equals(category)) {
//					continue;
//				}else {
//					reason+=","+category;
//				}
//			}
//		}else {
//			reason+=","+category;
//		}
		reason+=","+category;
		adminDao.updateblack(refuserid, reason);

	}

	@Override
	public List<BoardUserDTO> countsum(String userid) {
		return adminDao.countsun(userid);
	}

	@Override
	public void checkRole(String userid, String what) {
		Map<String, Object> user = userDao.selectUser(userid);
		if(user.get("AUTHORITY").equals("ROLE_BABY")) {
			List<BoardUserDTO> list = countsum(userid);
			int board = list.get(0).getBoard();
			int reply = list.get(0).getRply();
			if((what.equals("board") && board >= 4 && reply >= 10) || (what.equals("reply") && board >= 5 && reply >= 9)) {
				changeLevel(userid, "ROLE_MEMBER");
			}
		}
	}@Override
	public List<SanctionDTO> getreportreason(String refuserid) {
		return adminDao.getreportreason(refuserid);
	}
}
