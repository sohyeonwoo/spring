package com.java.service;

import java.util.List;

import com.java.dto.BoardDto;

public interface BService {

	//게시글 전체 불러오기
	List<BoardDto> selectAll();

	//사진 +  게시글 저장하기
	void bwrite(BoardDto bdto);

	//게시글 1개 가져오기
	BoardDto selectOne(int bno);

}
