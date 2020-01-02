package com.igo.board.service.cho;

import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.igo.board.model.cho.dao.ReplyDAO;
import com.igo.board.model.cho.dto.ReplyDTO;

@Service
public class UbReplyServiceImpl implements ReplyService {

	@Resource(name="ubReplyDAOImpl")
	ReplyDAO replyDAO;

	@Override
	public int count(int refIdx) {
		return replyDAO.count(refIdx);
	}

	@Override
	public List<ReplyDTO> list(int refIdx, int start, int end) {
		return replyDAO.list(refIdx, start, end);
	}
	
	@Override
	public void write(ReplyDTO replyDTO, Principal principal) {
		String userid = principal.getName();
		replyDTO.setrefWriter(userid);
		replyDAO.write(replyDTO);
	}

	@Override
	public void delete(int idx) {
		replyDAO.delete(idx);
	}

	@Override
	public void update(ReplyDTO replyDTO) {
		replyDAO.update(replyDTO);
	}

}
