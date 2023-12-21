package com.java.www.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.BoardDto;
import com.java.www.dto.EmpDepDto;
import com.java.www.dto.EmpDto;
import com.java.www.dto.MemBoardDto;
import com.java.www.dto.MemberDto;
import com.java.www.mapper.EmpMapper;

@Service
public class empServiceImpl implements EmpService {

	@Autowired
	EmpMapper empmapper;
	
	@Override
	public ArrayList<EmpDto> emplist() {
		ArrayList<EmpDto> list = empmapper.emplist(); 
		return list;
	}

	@Override
	public ArrayList<EmpDepDto> emplist2() {
		ArrayList<EmpDepDto> list = empmapper.emplist2(); 
		return list;
	}

	@Override
	public ArrayList<MemBoardDto> memBoardDto() {
		ArrayList<MemBoardDto> list = empmapper.memBoardDto();
		
		return list;
	}

}
