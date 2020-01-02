package com.igo.board.service.woo;
//example.security.service;

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

import com.igo.board.model.woo.dto.UserDTO;

public class UserAuthenticationService 
	implements UserDetailsService{

	@Inject
	SqlSessionTemplate sqlSession;
	
	@Inject
	BCryptPasswordEncoder passwordEncoder;
	
	public UserAuthenticationService() {}
	
	public UserAuthenticationService(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public UserDetails loadUserByUsername(String userid)
			throws UsernameNotFoundException {
		Map<String, Object> user =
				sqlSession.selectOne("user.selectUser", userid);
		if(user == null)
			throw new UsernameNotFoundException(userid);
		List<GrantedAuthority> authority = new ArrayList<>();
		authority.add(
				new SimpleGrantedAuthority(user.get("AUTHORITY").toString()));
		return new UserDTO(user.get("USERNAME").toString(),
				user.get("PASSWORD").toString(),
				(Integer)Integer.valueOf(user.get("ENABLE").toString())==1,
				true, true, true, authority,
				user.get("USERNAME").toString());
	}

}
