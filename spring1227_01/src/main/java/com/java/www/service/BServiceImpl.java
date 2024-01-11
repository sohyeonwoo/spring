package com.java.www.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.BCommentDto;
import com.java.www.dto.BoardDto;
import com.java.www.mapper.BoardMapper;

import jakarta.servlet.http.HttpSession;

@Service
public class BServiceImpl implements BService {

	@Autowired
	BoardMapper boardMapper;
	
	@Autowired
	HttpSession session;
	
	@Override //게시글 전체 가져오기
	public ArrayList<BoardDto> selectAll() {
		ArrayList<BoardDto> list = boardMapper.selectAll();
		return list;
	}

	@Override //게시글 1개 가져오기
	public Map<String, Object> selectOne(int bno) {
		//게시글 1개 가져오기
		BoardDto bdto = boardMapper.selectOne(bno);
		
		//하단댓글 가져오기
		List<BCommentDto> bCommentlist = boardMapper.bCommentSelectAll(bno);
		
		//Map전송
		Map<String, Object> map = new HashMap<>();
		map.put("bdto", bdto);
		map.put("bCommentlist", bCommentlist);
		return map;
	}

	@Override //댓글 1개 저장 후, 댓글 1개 가져오기
	public BCommentDto bCommentInsert(BCommentDto cdto) {
		cdto.setId((String) session.getAttribute("session_id"));
		
		//댓글 1개 저장 - cno / cdate 가 없음
		boardMapper.bCommentInsert(cdto); // 댓글폼에서 입력한 내용 저장시킴
		System.out.println("BServiceImpl bCommentInsert cno : "+cdto.getCno());
		System.out.println("BServiceImpl bCommentInsert cdate : "+cdto.getCdate());
		
		//댓글 1개 가져오기
		//BCommentDto bCommentDto = boardMapper.bCommentSelectOne(cdto.getCno()); // 댓글폼에서 입력한 내용 저장시킴
		
		return cdto;
	}

	//댓글삭제 
	@Override
	public String BCommentDelete(int cno) {
		String result ="";
		int re = boardMapper.BCommentDelete(cno);
		return result+re;
	}

	//댓글수정저장
	@Override
	public BCommentDto BCommentUpdate(BCommentDto cdto) {
		//session_id를 cdto의 id 에 저장
		cdto.setId((String) session.getAttribute("session_id"));
		//수정저장
		boardMapper.BCommentUpdate(cdto);
		//댓글 1개 가져오기
		BCommentDto bCommentDto = boardMapper.bCommentSelectOne(cdto.getCno()); // 댓글폼에서 입력한 내용 저장시킴
		return bCommentDto;
	}

	

}
