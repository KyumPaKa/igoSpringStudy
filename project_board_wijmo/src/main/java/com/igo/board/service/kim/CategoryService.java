package com.igo.board.service.kim;

import java.util.List;

import com.igo.board.model.kim.dto.CategoryDTO;

public interface CategoryService {
	List<CategoryDTO> listSv();
	CategoryDTO viewSv(int idx);
	void categoryUpdateSv(CategoryDTO dto);
	void categoryInsertSv(CategoryDTO dto);
	void CategoryDeleteSv(int idx);
}
