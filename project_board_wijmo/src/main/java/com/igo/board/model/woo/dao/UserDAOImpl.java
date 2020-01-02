package com.igo.board.model.woo.dao;

import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.igo.board.model.woo.dto.UserDTO;

@Repository
public class UserDAOImpl implements UserDAO {

	@Inject
	SqlSession sqlSession;
	
	@Override
	public int insertUser(Map<String, String> map) {
		return sqlSession.insert("user.insertUser", map);
	}

	@Override
	public Map<String, Object> selectUser(String userid) {
		return sqlSession.selectOne("user.selectUser", userid);
	}

	@Override
	public String resultid(String email) {
		 return sqlSession.selectOne("user.idfind", email);
	}
	
	@Override
	public void resultpwfind(Map<String, String> map) {
		sqlSession.selectOne("user.resetpwupdate", map);
	}
	
	@Override
	public String viewmember(String userid) {
		return sqlSession.selectOne("viewmember", userid);
	}

	@Override
	public void update(Map<String, String> map) {
		sqlSession.selectOne("user.update", map);
	}

	@Override
	public int idcheck(String userid) {
		return sqlSession.selectOne("idcheck", userid);
	}

	@Override
	public int emailcheck(String email) {
		return sqlSession.selectOne("emailcheck", email);
	}
	
}
