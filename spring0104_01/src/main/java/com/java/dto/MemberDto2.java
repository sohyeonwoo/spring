package com.java.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto2 {

	private String id;
	private String pw;
	private String name;
	private String gender;
	private String email;
	private String phone;
	private String hobby;
	private Timestamp mdate;
}
