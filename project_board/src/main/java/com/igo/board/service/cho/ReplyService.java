package com.igo.board.service.cho;

import java.security.Principal;
import java.util.List;

import com.igo.board.model.cho.dto.ReplyDTO;

public interface ReplyService {

	public int count(int refIdx);
	public List<ReplyDTO> list(int refIdx, int start, int end);
	public void write(ReplyDTO replyDTO, Principal principal);
	public void delete(int idx);
	public void update(ReplyDTO replyDTO);
	
}
