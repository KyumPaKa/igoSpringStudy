package com.igo.board.service.cho;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.igo.board.model.cho.dto.BoardDTO;

public interface BoardService {

	public List<BoardDTO> list(int start, int end, String search_option, String keyword, int refIdx);
	public int countArticle(String search_option, String keyword, int refIdx);
	public BoardDTO view(int idx, HttpServletResponse response, HttpServletRequest request);
	public int selectFeel(int idx, Principal principal);
	public int good(int idx, Principal principal);
	public int bad(int idx, Principal principal);
	public void delete(int idx);
	public void write(BoardDTO boardDTO);
	public void imgUpload(MultipartHttpServletRequest mRequest, int idx);
	public BoardDTO updateForm(int idx);
	public void update(BoardDTO boardDTO);
	public int replyCount(int idx);
	public void fileDelete(int idx);
	public int secretCheck(int refIdx);
	
}
