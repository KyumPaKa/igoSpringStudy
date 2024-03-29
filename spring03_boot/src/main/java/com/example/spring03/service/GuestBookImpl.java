package com.example.spring03.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.spring03.model.dao.GuestbookDAO;
import com.example.spring03.model.dto.GuestbookDTO;

@Service
public class GuestBookImpl implements GuestbookService {

	@Inject
	GuestbookDAO guestbookDao;
	
	@Override
	public List<GuestbookDTO> list() {
		return guestbookDao.list();
	}

	@Override
	public void insert(GuestbookDTO dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public GuestbookDTO view(int idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(GuestbookDTO dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int idx) {
		// TODO Auto-generated method stub

	}

}
