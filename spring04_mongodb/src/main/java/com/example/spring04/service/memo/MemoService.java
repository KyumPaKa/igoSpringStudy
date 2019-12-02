package com.example.spring04.service.memo;

import java.util.List;

import com.example.spring04.model.memo.dto.MemoDTO;

public interface MemoService {

	List<MemoDTO> getMemoList(); // 메모 목록
	void memoInsert(MemoDTO dto); // 메모 저장
	MemoDTO memoDetail(String _id); // 메모 상세 내용 보기
	void memoUpdate(MemoDTO dto); // 메모 수정
	void memoDelete(String _id); // 메모 삭제
	
}
