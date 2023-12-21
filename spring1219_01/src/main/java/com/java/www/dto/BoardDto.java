package com.java.www.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BoardDto {
	private int bno;
	private String btitle;
	private String bcontent;
	private Timestamp bdate;
	private String id;
	private int bgroup; //정렬할때 사용함 //댓글 답변달기할때 사용
	private int bstep; //답글달기 순서에 사용
	private int bindent; //답글달기에 사용
	private int bhit;
	private String bfile;
}
