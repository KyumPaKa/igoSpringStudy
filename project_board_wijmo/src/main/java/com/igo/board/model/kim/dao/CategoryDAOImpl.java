package com.igo.board.model.kim.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.igo.board.model.kim.dto.CategoryDTO;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<CategoryDTO> listDao() {
		return sqlSession.selectList("categoryListMP");
	}

	@Override
	public CategoryDTO viewDao(int idx) {
		return sqlSession.selectOne("categoryOneMP", idx);
	}

	@Override
	public void categoryUpdateDao(CategoryDTO dto) {
		sqlSession.update("categoryUpdateMP", dto);
	}

	@Override
	public void categoryInsertDao(CategoryDTO dto) {
		sqlSession.insert("categoryInsertMP", dto);
	}

	@Override
	public void CategoryDeleteDao(int idx) {
		sqlSession.delete("CategoryDeleteMP", idx);
	}

}
