package com.java.www.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.EmpDepDto;
import com.java.www.dto.EmpDto;
import com.java.www.dto.MemBoardDto;

@Mapper
public interface EmpMapper {

	ArrayList<EmpDto> emplist();

	ArrayList<EmpDepDto> emplist2();

	ArrayList<MemBoardDto> memBoardDto();

}
