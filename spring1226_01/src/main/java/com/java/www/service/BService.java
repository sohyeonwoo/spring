package com.java.www.service;

import java.util.List;
import java.util.Map;

import com.java.www.dto.BCommentDto;
import com.java.www.dto.BoardDto;

public interface BService {

	//공지사항 전체 가져오기
	List<BoardDto> selectAll();

	//공지사항 1개 보기
	Map<String, Object> selectOne(int bno);

	//DB에 댓글 저장
	BCommentDto BCommentInsert(BCommentDto cdto);

}
