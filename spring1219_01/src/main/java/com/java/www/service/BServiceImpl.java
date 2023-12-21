package com.java.www.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.BoardDto;
import com.java.www.mapper.BoardMapper;

@Service
public class BServiceImpl implements BService {

	@Autowired
	BoardMapper boardm;

	@Override
	public Map<String, Object> selectAllSearch(int page, String category, String searchWord) {
		// 하단 넘버링 - page , rowPerPage - 1페이지당 게시글 개수,// startPage,endPage,maxPage
		if(page<=0) page=1;
		int countPerPage = 10; // 1페이지당 게시글 수
		int bottomPerNum = 10;// 하단 넘버링 개수
		int countAll = boardm.selectSearchCount(category,searchWord); // 게시글 검색 총개수
		System.out.println("BServiceImpl CountAll : "+countAll);
		int maxPage = (int) Math.ceil((double) countAll / countPerPage);// Math.ceil : 나눠서 나머지가 나오면 올림.
		int startPage = ((page - 1) / 10) * 10 + 1; // ex) 5 - 1 = 4
													// 4를 10으로 나누면 0.4 > 0.4는 int값으로 "0" > 0*10+1
		int endPage = (startPage + bottomPerNum) - 1; // 마지막 페이지
		if (endPage > maxPage)
			endPage = maxPage;
		// startPage: 1 endPage: 10 // maxPage 5가 5이면
		// endPage 에 maxPage를 넣어서 1~10 나오는 것이 아니라 1~5까지만 나오도록 함.
		int startRow = (page - 1) * countPerPage + 1; // 1,11,21,31,41,51 ...
		int endRow = startRow + countPerPage - 1; // 10,20,30,40...
		// 데이터전송 - list,page,maxPage,startPage,endPage
		ArrayList<BoardDto> list = boardm.selectSearch(startRow, endRow,category,searchWord);
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("countAll", countAll);
		map.put("page", page);
		map.put("maxPage", maxPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		return map;
	}

	@Override // 게시글 전체 가져오기
	public Map<String, Object> selectAll(int page,String category, String searchWord) {
		// 하단 넘버링 - page , rowPerPage - 1페이지당 게시글 개수,// startPage,endPage,maxPage
		int countPerPage = 10; // 1페이지당 게시글 수
		int bottomPerNum = 10;// 하단 넘버링 개수
		int countAll = boardm.selectCountAll(category,searchWord); // 게시글 총개수
		int maxPage = (int) Math.ceil((double) countAll / countPerPage);// Math.ceil : 나눠서 나머지가 나오면 올림.
		int startPage = ((page - 1) / 10) * 10 + 1; // ex) 5 - 1 = 4
													// 4를 10으로 나누면 0.4 > 0.4는 int값으로 "0" > 0*10+1
		int endPage = (startPage + bottomPerNum) - 1; // 마지막 페이지
		if (endPage > maxPage)
			endPage = maxPage;
		// startPage: 1 endPage: 10 // maxPage 5가 5이면
		// endPage 에 maxPage를 넣어서 1~10 나오는 것이 아니라 1~5까지만 나오도록 함.
		int startRow = (page - 1) * countPerPage + 1; // 1,11,21,31,41,51 ...
		int endRow = startRow + countPerPage - 1; // 10,20,30,40...
		// 데이터전송 - list,page,maxPage,startPage,endPage
		ArrayList<BoardDto> list = boardm.selectAll(startRow, endRow,category,searchWord);
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("countAll", countAll);
		map.put("page", page);
		map.put("maxPage", maxPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);

		return map;
	}

	@Override // 게시글 1개 가져오기 //이전글 , 다음글 가져오기 : 3개
	public Map<String, Object> SelectOne(int bno) {
		BoardDto bdto = boardm.selectOne(bno);
		BoardDto prevdto = boardm.selectOnePrev(bno);
		BoardDto nextdto = boardm.selectOneNext(bno);
		// 조회수 1증가
		boardm.bhitUp(bno);
		Map<String, Object> map = new HashMap<>();
		map.put("bdto", bdto);
		map.put("prevdto", prevdto);
		map.put("nextdto", nextdto);

		return map;
	}

	@Override
	public void bInsert(BoardDto bdto) {
		int result = boardm.bInsert(bdto);
		System.out.println("BServiceImpl" + result);
	}

	// 게시글 삭제
	@Override
	public void bDelete(int bno) {
		int result = boardm.bDelete(bno);
		System.out.println("bserImpe bDelete result : " + result);

	}

	// 게시글 수정후 저장
	@Override
	public void doBUpdate(BoardDto bdto) {
		int result = boardm.doBUpdate(bdto);
		System.out.println("bserImpe doBUpdate result : " + result);
	}

	// 답변달기 저장
	@Override
	public void doBReply(BoardDto bdto) {
		// 답변달기 저장 = bgroup , bstep , bindent 필요 // bdto에 담겨있음
		// 1.부모보다 큰 bstep은 1씩 증가 시킴
		// 2.bindent는 부모의 1+
		// 3.현재글의 부모 bstep 1+
		// 4.bgroup은 부모와 같음.
		boardm.bstepUp(bdto);
		int result = boardm.doBReply(bdto);
	}

}
