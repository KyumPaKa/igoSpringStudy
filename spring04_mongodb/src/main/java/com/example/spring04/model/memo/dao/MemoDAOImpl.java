package com.example.spring04.model.memo.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.example.spring04.model.memo.dto.MemoDTO;

@Component("memoDao")
public class MemoDAOImpl implements MemoDAO {

	@Autowired
	MongoTemplate mongoTemplate;
	
	static String COLLECTION_NAME="memo";
	
	@Override
	public List<MemoDTO> getMemoList() {
		Query query = new Query();
		// 내림차순 정렬
		query.with(new Sort(Sort.Direction.DESC, "post_date"));
		return (List<MemoDTO>)mongoTemplate.find(query, MemoDTO.class, COLLECTION_NAME);
	}

	@Override
	public void memoInsert(MemoDTO dto) {
		dto.setPost_date(new Date());
		mongoTemplate.insert(dto, COLLECTION_NAME);
	}

	@Override
	public MemoDTO getmemoDetail(String _id) {
		return mongoTemplate.findById(_id, MemoDTO.class, COLLECTION_NAME);
	}

	@Override
	public void memoUpdate(MemoDTO dto) {
		Query query = new Query(new Criteria("_id").is(dto.get_id()));
		Update update = new Update();
		update.set("writer", dto.getWriter());
		update.set("memo", dto.getMemo());
		mongoTemplate.upsert(query, update, MemoDTO.class, COLLECTION_NAME);
	}

	@Override
	public void memoDelete(String _id) {
		Query query = new Query(new Criteria("_id").is(_id));
		mongoTemplate.remove(query, COLLECTION_NAME);
	}

}
