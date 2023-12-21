package com.java.www.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.BoardDto;
import com.java.www.mapper.BoardMapper;

@Service
public class BServiceImpl implements BService {
	@Autowired
	BoardMapper boardM; //BoardMapper interface 연결
	
	@Override
	public ArrayList<BoardDto> selectAll() {
		ArrayList<BoardDto> list = boardM.selectAll();
		return list;
	}

}
