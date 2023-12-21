package com.java.www.boardDto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor//전체생성자
@NoArgsConstructor//기본생성자
@Data// getter,setter 입력
public class BoardDto {
	private String id;
	private String pw;
	private String name;
	private String phone;
	private String gender;
	private String hobby;
	private Timestamp mdate;

}
