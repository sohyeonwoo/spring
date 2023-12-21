package com.java.www.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.BoardDto;

@Mapper
public interface BoardMapper {
	//불안전한 메소드 생성 
	//메소드 생성
	ArrayList<BoardDto> selectAll();

}
