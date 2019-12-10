package com.igo.board.service.cho;

import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.igo.board.model.cho.dao.BoardDAO;
import com.igo.board.model.cho.dto.BoardDTO;
import com.igo.board.model.cho.dto.FeelDTO;

@Service
@Transactional
public class MemoServiceImpl implements BoardService {

	@Resource(name="memoDAOImpl")
	BoardDAO boardDao;
	
	@Override
	public List<BoardDTO> list(int start, int end, String search_option, String keyword, int refIdx) {
		return boardDao.list(start, end, search_option, keyword, refIdx);
	}

	@Override
	public int countArticle(String search_option, String keyword, int refIdx) {
		return boardDao.countArticle(search_option, keyword, refIdx);
	}

	@Override
	public BoardDTO view(int idx, HttpServletResponse response, HttpServletRequest request) {
		return null;
	}
	
	@Override
	public int selectFeel(int idx, Principal principal) {
		String userid = principal.getName();
		FeelDTO feelDTO = new FeelDTO();
		feelDTO.setRefUserid(userid);
		feelDTO.setRefIdx(idx);
		return boardDao.selectFeel(feelDTO);
	}

	@Override
	@Transactional
	public int good(int idx, Principal principal) {
		String userid = principal.getName();
		
		FeelDTO feelDTO = new FeelDTO();
		feelDTO.setRefUserid(userid);
		feelDTO.setRefIdx(idx);
		// 좋아요: 0 싫어요: 1
		feelDTO.setFeel(0);
		if(boardDao.selectFeelCnt(feelDTO) == 0) {
			feelDTO.setFeel(1);
			if(boardDao.selectFeelCnt(feelDTO) == 0) { // 처음 누름
				feelDTO.setFeel(0);
				boardDao.insertFeel(feelDTO);
				boardDao.increaseGoodCnt(idx);
				return 1;
			} else { // 싫어요 -> 좋아요
				boardDao.deleteFeel(feelDTO);
				feelDTO.setFeel(0);
				boardDao.insertFeel(feelDTO);
				boardDao.decreaseBadCnt(idx);
				boardDao.increaseGoodCnt(idx);
				return 3;
			}
		} else { // 좋아요 취소
			boardDao.deleteFeel(feelDTO);
			boardDao.decreaseGoodCnt(idx);
			return 2;
		}
	}

	@Override
	@Transactional
	public int bad(int idx, Principal principal) {
		String userid = principal.getName();
		
		FeelDTO feelDTO = new FeelDTO();
		feelDTO.setRefUserid(userid);
		feelDTO.setRefIdx(idx);
		// 좋아요: 0 싫어요: 1
		feelDTO.setFeel(1);
		if(boardDao.selectFeelCnt(feelDTO) == 0) {
			feelDTO.setFeel(0);
			if(boardDao.selectFeelCnt(feelDTO) == 0) { // 처음 누름
				feelDTO.setFeel(1);
				boardDao.insertFeel(feelDTO);
				boardDao.increaseBadCnt(idx);
				return 1;
			} else { // 좋아요 -> 싫어요
				boardDao.deleteFeel(feelDTO);
				feelDTO.setFeel(1);
				boardDao.insertFeel(feelDTO);
				boardDao.decreaseGoodCnt(idx);
				boardDao.increaseBadCnt(idx);
				return 3;
			}
		} else { // 싫어요 취소
			boardDao.decreaseBadCnt(idx);
			boardDao.deleteFeel(feelDTO);
			return 2;
		}
	}

	@Override
	public void delete(int idx) {
		boardDao.delete(idx);
	}

	@Override
	public void write(BoardDTO boardDTO) {
		boardDao.write(boardDTO);
	}

	@Override
	public void imgUpload(MultipartHttpServletRequest mRequest, int idx) {
		
	}

	@Override
	public BoardDTO updateForm(int idx) {
		return null;
	}

	@Override
	public void update(BoardDTO boardDTO) {
		boardDao.update(boardDTO);
	}

	@Override
	public int replyCount(int idx) {
		return 0;
	}

	@Override
	public void fileDelete(int idx) {
		
	}

	@Override
	public int secretCheck(int refIdx) {
		return 0;
	}
	
}
