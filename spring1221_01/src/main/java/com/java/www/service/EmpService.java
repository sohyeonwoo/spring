package com.java.www.service;

import java.util.ArrayList;

import com.java.www.dto.EmpDepDto;
import com.java.www.dto.EmpDto;
import com.java.www.dto.MemBoardDto;

public interface EmpService {

	ArrayList<EmpDto> emplist();

	ArrayList<EmpDepDto> emplist2();

	ArrayList<MemBoardDto> memBoardDto();

}
