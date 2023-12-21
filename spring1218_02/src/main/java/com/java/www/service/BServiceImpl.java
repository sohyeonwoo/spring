package com.java.www.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.BoardDto;
import com.java.www.mapper.BoardMapper;

@Service
public class BServiceImpl implements BService {
	@Autowired
	BoardMapper boardM;
	@Override //게시글 전체 가져오기
	public ArrayList<BoardDto> bList() {
		ArrayList<BoardDto> list = boardM.bList();
		return list;
	}
	@Override
	public BoardDto selectOne(int bno) {
		BoardDto boardDto = boardM.selectOne(bno);
		return boardDto;
	}
	@Override
	public void bInsert(BoardDto bdto) {
	}

	
}
