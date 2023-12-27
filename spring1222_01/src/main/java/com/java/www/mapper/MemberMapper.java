package com.java.www.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.MemberDto;

@Mapper
public interface MemberMapper {

	//로그인 확인
	MemberDto login(String id,String pw);

	//회원가입시 아이디 체크부분
	MemberDto idCheck(String id);

	//회원가입 
    void join02confirm(MemberDto mdto);

}
