package com.java.www.service;

import org.springframework.stereotype.Service;

@Service
public class PServiceImpl2 implements PService {

	@Override
	public void execute() {
		System.out.println("신규 service 버전2 호출합니다");

	}

}
