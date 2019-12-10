package com.igo.board.model.cho.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.igo.board.model.cho.dto.ReplyDTO;

@Repository
public class FbReplyDAOImpl implements ReplyDAO {

	@Resource
	SqlSession sqlSession;

	@Override
	public void write(ReplyDTO replyDTO) {
		sqlSession.insert("fbReply.write", replyDTO);
	}

	@Override
	public int count(int refIdx) {
		return sqlSession.selectOne("fbReply.count", refIdx);
	}

	@Override
	public List<ReplyDTO> list(int refIdx, int start, int end) {
		Map<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		map.put("refIdx", refIdx);
		return sqlSession.selectList("fbReply.list", map);
	}

	@Override
	public void delete(int idx) {
		sqlSession.update("fbReply.delete", idx);
	}

	@Override
	public void update(ReplyDTO replyDTO) {
		sqlSession.update("fbReply.update", replyDTO);
	}
	
}
