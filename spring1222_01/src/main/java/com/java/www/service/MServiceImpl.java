package com.java.www.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.MemberDto;
import com.java.www.mapper.MemberMapper;

@Service
public class MServiceImpl implements MService {

	@Autowired
	MemberMapper membermapper;
	
	@Override
	public MemberDto login(String id, String pw) {
		MemberDto mdto = membermapper.login(id,pw);
		return mdto;
	}

	//회원가입 아이디 체크
	@Override
	public String idCheck(String id) {
		String result = "사용불가";
		MemberDto mdto = membermapper.idCheck(id);
		if(mdto==null) result="사용가능";
		
		return result;
	}

	@Override
	public String join02confirm(MemberDto mdto) {
		membermapper.join02confirm(mdto);
		String result = "가입성공";
		return result;
	}

	


}
