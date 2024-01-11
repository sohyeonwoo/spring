package com.java.www.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IncomeDto {
	private String cyear;
	private String cmonth;
	private long csale;
	
}