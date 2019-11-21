package com.example.security.model.dao;

import java.util.Map;

public interface UserDAO {
	// 회원가입 처리
	public int insertUser(Map<String, String> map);
	// 회원 상세 정보
	public Map<String, Object> selectUser(String userid);
	
}
