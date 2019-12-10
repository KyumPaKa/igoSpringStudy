package com.igo.board.service.cho;

import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.igo.board.model.cho.dao.ReplyDAO;
import com.igo.board.model.cho.dto.ReplyDTO;

@Service
public class FbReplyServiceImpl implements ReplyService {

	@Resource(name="fbReplyDAOImpl")
	ReplyDAO replyDao;

	@Override
	public int count(int refIdx) {
		return replyDao.count(refIdx);
	}

	@Override
	public List<ReplyDTO> list(int refIdx, int start, int end) {
		return replyDao.list(refIdx, start, end);
	}
	
	@Override
	public void write(ReplyDTO replyDTO, Principal principal) {
		String userid = principal.getName();
		replyDTO.setrefWriter(userid);
		replyDao.write(replyDTO);
	}

	@Override
	public void delete(int idx) {
		replyDao.delete(idx);
	}

	@Override
	public void update(ReplyDTO replyDTO) {
		replyDao.update(replyDTO);
	}

}
