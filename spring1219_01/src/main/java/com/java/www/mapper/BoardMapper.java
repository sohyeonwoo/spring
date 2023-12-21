package com.java.www.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.BoardDto;

@Mapper
public interface BoardMapper {

	// 게시글 전체 가져오기 select //데이타 가져오는 것 : select
	ArrayList<BoardDto> selectAll(int startRow, int endRow, String category, String searchWord);
	ArrayList<BoardDto> selectSearch(int startRow, int endRow, String category, String searchWord);

	// 게시글 총 개수 , 게시글 검색 개수
	int selectCountAll(String category, String searchWord);
	//게시글 검색 총 개수
	int selectSearchCount(String category, String searchWord);

	// 게시글 1개 가져오기 select //데이타 가져오는 것 : select
	BoardDto selectOne(int bno);

	// 조회수 1증가
	void bhitUp(int bno);

	// 게시글 1개 가져오기 / 이전글로 가져오기 select //데이타 가져오는 것 : select
	BoardDto selectOnePrev(int bno);

	// 게시글 1개 가져오기 / 다음글로 가져오기 select //데이타 가져오는 것 : select
	BoardDto selectOneNext(int bno);

	// 게시글 쓰기 저장 insert //데이터 저장하기 : insert
	int bInsert(BoardDto bdto);

	// 게시글 삭제 delete //데이터 삭제 Delete
	int bDelete(int bno);

	// 게시글 수정후 저장 update //데이터 수정후 저장 update
	int doBUpdate(BoardDto bdto);

	// 답변달기 저장 후
	// 답변달기 bstep 1증가
	void bstepUp(BoardDto bdto);

	int doBReply(BoardDto bdto);

	

	

}
