package com.example.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.security.model.dto.UserDTO;

public class UserAuthenticationService implements UserDetailsService {

	@Inject
	SqlSessionTemplate sqlSession;
	
	@Inject
	BCryptPasswordEncoder passwordEncoder;
	
	public UserAuthenticationService() {
		
	}
	public UserAuthenticationService(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	// 로그인 인증을 처리하는 코드
	// 파라미터로 입력된 아이디값에 해당하는 테이블의 레코드를 읽어옴
	// 정보가 없으면 예외를 발생시킴
	// 정보가 있으면 해당 정보가 map(dto)로 리턴됨
	@Override
	public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
		Map<String, Object> user = sqlSession.selectOne("user.selectUser", userid);
		System.out.println("loadUserByUsername : " + user);
		if(user == null)
			throw new UsernameNotFoundException(userid);
		List<GrantedAuthority> authority = new ArrayList<>();
		// 오라클에서는 필드명을 대문자로 적어야 함
		// 오라클에서는 BigInteger 관련 오류가 발생할 수 있으므로 아래와 같이 처리
		// (Integer)Integer.valueOf(user.get("ENABLED").toString()) == 1
		authority.add(new SimpleGrantedAuthority(user.get("AUTHORITY").toString()));
		return new UserDTO(user.get("USERNAME").toString(), user.get("PASSWORD").toString(), (Integer)Integer.valueOf(user.get("ENABLED").toString()) == 1, 
				true, true, true, authority, user.get("USERNAME").toString());
	}

}
