package com.java.www.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.BCommentDto;
import com.java.www.dto.BoardDto;

@Mapper
public interface BoardMapper {


	//게시글 전체보기
	List<BoardDto> selectAll();

	//게시글 한개보기
	BoardDto selectOne(int bno);

	//댓글가져오기
	List<BCommentDto> BCommentselectAll(int bno);

	//db에 댓글 저장하기
	void BCommentInsert(BCommentDto cdto);

	//저장된 댓글 가져오기
	BCommentDto BCommentSelectOne(int cno);

}
