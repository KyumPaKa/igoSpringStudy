package com.igo.board.model.cho.dao;

import java.util.List;

import com.igo.board.model.cho.dto.AttachDTO;
import com.igo.board.model.cho.dto.BoardDTO;
import com.igo.board.model.cho.dto.FeelDTO;

public interface BoardDAO {

	public List<BoardDTO> list(int start, int end, String search_option, String keyword, int refIdx);
	public int countArticle(String search_option, String keyword, int refIdx);
	public int countArticle2(String search_option, String keyword, int refIdx);
	public BoardDTO view(int idx);
	public int selectFeel(FeelDTO feelDTO);
	public void increaseViewCnt(int idx);
	public void increaseGoodCnt(int idx);
	public void decreaseGoodCnt(int idx);
	public void increaseBadCnt(int idx);
	public void decreaseBadCnt(int idx);
	public void insertFeel(FeelDTO feelDTO);
	public int selectFeelCnt(FeelDTO feelDTO);
	public void deleteFeel(FeelDTO feelDTO);
	public void delete(int idx);
	public List<AttachDTO> selectAttach(int refIdx);
	public void write(BoardDTO boardDTO);
	public void insertAttach(AttachDTO attachDTO);
	public void update(BoardDTO boardDTO);
	public int replyCount(int idx);
	public void fileDelete(int idx);
	public int secretCheck(int refIdx);
	
}
