package com.java.www.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.java.www.dto.BoardDto;

@Service
public interface BService {
	//게시글 전체 가져오기
	ArrayList<BoardDto> bList();

	//게시글 1개 가져오기
	BoardDto selectOne(int bno);
	
	//
	void bInsert(BoardDto bdto);

}
