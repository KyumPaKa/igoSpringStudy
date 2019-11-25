package com.example.spring04.model.member.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.example.spring04.model.member.dto.MemberDTO;

@Component("memberDao")
public class MemberDAOImpl implements MemberDAO {

	@Autowired // 의존관계 주입
	MongoTemplate mongoTemplate;
	
	static String COLLECTION_NAME = "member"; // collection(테이블) 이름
	
	@Override
	public MemberDTO loginCheck(String userid, String passwd) {
		// _id와 passwd가 같은 document(레코드)를 찾음
		Query query = new Query(new Criteria("_id").is(userid).and("passwd").is(passwd));
		// get(0) 첫번째 document
		List<MemberDTO> items = mongoTemplate.find(query, MemberDTO.class, COLLECTION_NAME);
		System.out.println(items);
		if(items.size() > 0) {
			return items.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void join(MemberDTO member) {
		// document 저장
		mongoTemplate.insert(member, COLLECTION_NAME);
	}

}
