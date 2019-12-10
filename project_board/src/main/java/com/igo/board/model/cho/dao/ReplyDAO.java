package com.igo.board.model.cho.dao;

import java.util.List;

import com.igo.board.model.cho.dto.ReplyDTO;

public interface ReplyDAO {
	
	public void write(ReplyDTO replyDTO);
	public int count(int refIdx);
	public List<ReplyDTO> list(int refIdx, int start, int end);
	public void delete(int idx);
	public void update(ReplyDTO replyDTO);
	
}
