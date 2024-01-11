package com.java.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.BoardDto;
import com.java.dto.MemberDto;

@Mapper
public interface BoardMapper {

	//게시글 전체 가져오기
	List<BoardDto> selectAll();

	//service 연결 : 글쓰기 저장
	void bwrite(BoardDto bdto);

	//게시글 1개 가져오기
	BoardDto selectOne(int bno);

	//로그인 
	MemberDto login(MemberDto mdto);

	//게시글 쓰기
	void bInsert(BoardDto bdto);

}
