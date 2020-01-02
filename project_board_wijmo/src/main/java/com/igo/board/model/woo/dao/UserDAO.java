package com.igo.board.model.woo.dao;

import java.util.Map;

import com.igo.board.model.woo.dto.UserDTO;

public interface UserDAO {

	public int insertUser(Map<String, String> map);
	public Map<String, Object> selectUser(String userid);
	public String resultid(String email);
	public void resultpwfind(Map<String, String> map);
	public String viewmember(String userid);
	public void update(Map<String, String> map);
	public int idcheck(String userid);
	public int emailcheck(String email);
	
}
