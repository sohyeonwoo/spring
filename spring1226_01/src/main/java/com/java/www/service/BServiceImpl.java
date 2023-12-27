package com.java.www.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.BCommentDto;
import com.java.www.dto.BoardDto;
import com.java.www.mapper.BoardMapper;

@Service
public class BServiceImpl implements BService {
	@Autowired
	BoardMapper boardMapper;

	//공지사항 전체 보기
	@Override
	public List<BoardDto> selectAll() {
		List<BoardDto> list = boardMapper.selectAll();
		return list;
	}

	//공지사항 1개 보기
	@Override
	public Map<String,Object> selectOne(int bno) {
		// map : 게시글과 게시글에 포함된 댓글전체가져오기
		Map<String,Object> map = new HashMap<>() ;
		BoardDto bdto = boardMapper.selectOne(bno);
		List<BCommentDto> list = boardMapper.BCommentselectAll(bno);
		map.put("bdto",bdto);
		map.put("list",list);
		return map;
	}

	//DB에 댓글 1개 저장하기
	@Override
	public BCommentDto BCommentInsert(BCommentDto cdto) {
		//임시
		BCommentDto bCommentDto = new BCommentDto();
		boardMapper.BCommentInsert(cdto);
		System.out.println("BServiceImpl BCommentInsert cno"+cdto.getCno());
		System.out.println("BServiceImpl BCommentInsert cdate"+cdto.getCdate());
		//저장된 댓글 1개 가져오기
		BCommentDto bCommeDto = boardMapper.BCommentSelectOne(cdto.getCno());
		return bCommentDto;
	}

}
