package com.igo.board.service.cho;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.igo.board.model.cho.dao.BoardDAO;
import com.igo.board.model.cho.dto.AttachDTO;
import com.igo.board.model.cho.dto.BoardDTO;
import com.igo.board.model.cho.dto.FeelDTO;

@Service
public class FreeBoardServiceImpl implements BoardService {

	@Resource(name="freeBoardDAOImpl")
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
		Cookie[] cookies = request.getCookies();
		Cookie viewCookie = null;
		
		if (cookies != null && cookies.length > 0) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("cookie" + idx)) {
                    viewCookie = cookies[i];
                }
            }
        }
		if (viewCookie == null) {
			Cookie newCookie = new Cookie("cookie" + idx, "|" + idx + "|");
			newCookie.setMaxAge(24*60*60);
			response.addCookie(newCookie);
			boardDao.increaseViewCnt(idx);
		}
		BoardDTO result = boardDao.view(idx);
		result.setAttachList(boardDao.selectAttach(idx));
		return result;
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
			boardDao.decreaseGoodCnt(idx);
			boardDao.deleteFeel(feelDTO);
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
		// 다중 파일 업로드
		try {
			String uploadPath = mRequest.getSession().getServletContext().getRealPath("/") + "resources/freeBoard/images/"; // 사진 저장위치
			List<MultipartFile> fileList = mRequest.getFiles("file");
			for(MultipartFile filePart : fileList) {
				String originalFileName = filePart.getOriginalFilename();
				UUID uuid = UUID.randomUUID(); // 사진 이름 중복 방지
				if (originalFileName != "") {
					String saveFileName = uuid + "_" + originalFileName;

					byte[] data = filePart.getBytes();
					new File(uploadPath + "\\" + saveFileName);
					FileOutputStream fos = new FileOutputStream(uploadPath + "\\" + saveFileName);
					fos.write(data);
					fos.close();
					
					AttachDTO attachDTO = new AttachDTO();
					if(idx != -1) {
						if(idx == 0) attachDTO.setRefIdx(boardDao.countArticle2("all", "", 2) + 1);
						else attachDTO.setRefIdx(idx);
						attachDTO.setFileName(saveFileName);
						boardDao.insertAttach(attachDTO);
					}
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public BoardDTO updateForm(int idx) {
		BoardDTO result = boardDao.view(idx);
		result.setAttachList(boardDao.selectAttach(idx));
		return result;
	}

	@Override
	public void update(BoardDTO boardDTO) {
		boardDao.update(boardDTO);
	}

	@Override
	public int replyCount(int idx) {
		return boardDao.replyCount(idx);
	}

	@Override
	public void fileDelete(int idx) {
		boardDao.fileDelete(idx);
	}

	@Override
	public int secretCheck(int refIdx) {
		return boardDao.secretCheck(refIdx);
	}
	
}
