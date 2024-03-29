package com.example.spring01.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.spring01.model.dao.MemberDAO;
import com.example.spring01.model.dao.MemberDAOImpl;
import com.example.spring01.model.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject // 스프링 컨테이너가 만든 dao 객체가 연결됨(의존관계 주입)
	MemberDAO memberDAO;
	
	@Override
	public List<MemberDTO> memberList() {
		return memberDAO.memberList();
	}

	@Override
	public void insertMember(MemberDTO dto) {
		memberDAO.insertMember(dto);
	}

	@Override
	public MemberDTO viewMember(String userid) {
		return memberDAO.viewMember(userid);
	}

	@Override
	public void deleteMember(String userid) {
		memberDAO.deleteMember(userid);
	}

	@Override
	public void updateMember(MemberDTO dto) {
		memberDAO.updateMember(dto);
	}

	@Override
	public boolean checkPw(String userid, String passwd) {
		return memberDAO.checkPw(userid, passwd);
	}

}
