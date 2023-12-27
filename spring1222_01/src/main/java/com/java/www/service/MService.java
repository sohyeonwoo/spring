package com.java.www.service;

import java.util.ArrayList;

import com.java.www.dto.MemberDto;

public interface MService {

	//로그인 확인
	MemberDto login(String id, String pw);

	//회원가입 아이디체크 부분
	String idCheck(String id);

	//회원가입
	String join02confirm(MemberDto mdto);





}
