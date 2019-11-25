package com.example.spring04.service.member;

import com.example.spring04.model.member.dto.MemberDTO;

public interface MemberService {

	MemberDTO loginCheck(String userid, String passwd);
	void join(MemberDTO member);
	
}
