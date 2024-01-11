package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.BoardDto;
import com.java.dto.MemberDto;
import com.java.mapper.BoardMapper;

import jakarta.servlet.http.HttpSession;

@Service
public class BServiceImpl implements BService {

	@Autowired BoardMapper boardMapper;
	@Autowired HttpSession session;
	
	@Override //게시글 전체 가져오기
	public List<BoardDto> selectAll() {
		List<BoardDto> list = boardMapper.selectAll();
		return list;
	}

	//service 연결 : 글쓰기 저장
	@Override
	public void bwrite(BoardDto bdto) {

		boardMapper.bwrite(bdto);	
	}

	//게시글 1개 가져오기 
	@Override
	public BoardDto selectOne(int bno) {
		BoardDto bdto = boardMapper.selectOne(bno);
		return bdto;
	}

	//로그인
	@Override
	public MemberDto login(MemberDto mdto) {
		MemberDto mdto1 = boardMapper.login(mdto);
		return mdto1;
	}

	@Override
	public void bInsert(BoardDto bdto) {
		boardMapper.bInsert(bdto);
		
	}

}
