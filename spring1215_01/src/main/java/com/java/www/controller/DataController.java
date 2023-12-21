package com.java.www.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.www.dto.MemberDto;

@Controller
public class DataController {
	@RequestMapping("mInsert")
	public String mInsert() {
		return "mInsert";
	}
	@ResponseBody
	@RequestMapping("boardBno/{bno}/{bhit}")
	public String boardBno(@PathVariable int bno,@PathVariable int bhit) {
		String txt = "boardBno 글번호 : "+ bno;
		txt += ",조회수 bhit  : "+ bhit;
		
		
		return txt;
	}
	@ResponseBody
	@RequestMapping("idCheck")
	public Map<String,Object> idCheck(String id) {
		//db검색 select * from member where id=?
		Map<String,Object> map = new HashMap<>();
		ArrayList<MemberDto> list = new ArrayList<>();
		if(id.equals("aaa")) {
			map.put("result","fail"); //불가능
		}else {
			map.put("result","success"); //가능
		}
		list.add(new MemberDto("bbb","1111","소우","010-1111=1111","M","golf",new Timestamp(System.currentTimeMillis())));
		map.put("name","소현우");
		map.put("phone","010-111-111");
		map.put("bno",1);
		map.put("mdto",new MemberDto("bbb","1111","소현우","010-1111=1111","M","golf",new Timestamp(System.currentTimeMillis())));
		map.put("list", list);
		//Json 포맷 자동 변환 
		return map;
	}
	
	
	
	@RequestMapping("aaa")
	public String aaa() {
		return "aaa";
	}
	@ResponseBody //데이터로 리턴함 
	@RequestMapping("bbb")
	public String bbb() {
		String txt="{'id':'aaa','pw':'1111','name':'소현우'}";
		return txt;
	}

}
