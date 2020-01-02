package com.igo.board.service.kim;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.igo.board.model.kim.dao.CategoryDAO;
import com.igo.board.model.kim.dto.CategoryDTO;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Inject
	CategoryDAO Categorydao;
	
	@Override
	public List<CategoryDTO> listSv() {
		return Categorydao.listDao();
	}

	@Override
	public CategoryDTO viewSv(int idx) {
		return Categorydao.viewDao(idx);
	}

	@Override
	public void categoryUpdateSv(CategoryDTO dto) {
		Categorydao.categoryUpdateDao(dto);
	}

	@Override
	public void categoryInsertSv(CategoryDTO dto) {
		Categorydao.categoryInsertDao(dto);
	}

	@Override
	public void CategoryDeleteSv(int idx) {
		Categorydao.CategoryDeleteDao(idx);
	}

}
