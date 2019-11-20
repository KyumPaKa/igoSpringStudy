package com.example.spring02.service.message;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring02.model.message.dao.MessageDAO;
import com.example.spring02.model.message.dao.PointDAO;
import com.example.spring02.model.message.dto.MessageDTO;

@Service
public class MessageServiceImpl implements MessageService {

	@Inject
	MessageDAO messageDao;
	
	@Inject
	PointDAO pointDao;
	
	@Transactional // method 내부의 코드를 트랜잭션(거래처리 단위)으로 묶음
	@Override
	public void addMessage(MessageDTO dto) {
		messageDao.create(dto);
		pointDao.updatePoint(dto.getSender(), 10);
	}

	@Override
	public MessageDTO readMessage(String userid, int mid) {
		// TODO Auto-generated method stub
		return null;
	}

}
