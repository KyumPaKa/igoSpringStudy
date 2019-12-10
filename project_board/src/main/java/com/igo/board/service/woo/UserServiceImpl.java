package com.igo.board.service.woo;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.igo.board.model.woo.dao.UserDAO;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	UserDAO dao;

	@Override
	public String resultid(String email) {
		return dao.resultid(email); 		
	}
	
	@Override
	public String viewMember(String userid) {
		return dao.viewmember(userid);
	}
//	@Override
//	public String viewMember(String userid) {
//		// TODO Auto-generated method stub
//		return dao.viewmember(userid);
//	}

	@Override
	public int idcheck(String userid) {
		return dao.idcheck(userid);
	}

	@Override
	public int emailcheck(String email) {
		return dao.emailcheck(email);
	}
}
