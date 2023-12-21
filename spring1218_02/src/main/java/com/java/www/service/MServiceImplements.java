package com.java.www.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.MemberDto;
import com.java.www.mapper.MemberMapper;

@Service //상속받기
public class MServiceImplements implements MService {
	@Autowired
	MemberMapper memberMapper;

	@Override
	public MemberDto loginSelect(MemberDto mdto) {
		MemberDto memberDto = memberMapper.loginSelect(mdto);
		return memberDto;
	}
	
	
	

}
