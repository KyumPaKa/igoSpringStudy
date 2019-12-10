package com.igo.board.model.kim.dao;

import java.util.List;

import com.igo.board.model.kim.dto.CategoryDTO;

public interface CategoryDAO {

	List<CategoryDTO> listDao();
	CategoryDTO viewDao(int idx);
	void categoryUpdateDao(CategoryDTO dto);
	void categoryInsertDao(CategoryDTO dto);
	void CategoryDeleteDao(int idx);
}
