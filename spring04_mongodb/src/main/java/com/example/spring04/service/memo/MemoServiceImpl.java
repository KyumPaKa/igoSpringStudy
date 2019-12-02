package com.example.spring04.service.memo;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.spring04.model.memo.dao.MemoDAO;
import com.example.spring04.model.memo.dto.MemoDTO;

@Component("memoService")
public class MemoServiceImpl implements MemoService {

	MemoDAO memoDao;
	
	@Override
	public List<MemoDTO> getMemoList() {
		return memoDao.getMemoList();
	}

	@Override
	public void memoInsert(MemoDTO dto) {
		memoDao.memoInsert(dto);
	}

	@Override
	public MemoDTO memoDetail(String _id) {
		return memoDao.getmemoDetail(_id);
	}

	@Override
	public void memoUpdate(MemoDTO dto) {
		memoDao.memoUpdate(dto);
	}

	@Override
	public void memoDelete(String _id) {
		memoDao.memoDelete(_id);
	}

}
