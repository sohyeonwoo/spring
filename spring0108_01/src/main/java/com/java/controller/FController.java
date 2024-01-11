package com.java.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class FController {
	
	@GetMapping("/")
	public String main() {
		return "main";
	}
	
	@GetMapping("login")
	public String login() {
		return "login";
	}

	
	//카카오 로그인
	@GetMapping("kkk")
	@ResponseBody
	public String oauth(String code) {
		
		//코드값 받기
		System.out.println("kakao code: "+ code);
		String tokenUrl="https://kauth.kakao.com/oauth/token";
		String content_type="application/x-www-form-urlencoded;charset=utf-8";
		String grant_type = "authorization_code";
		String client_id = "597a207cf8fbf83510a2bfcaedc70b30";
		String redirect_uri = "http://localhost:8000/kakao/oauth";
		//String code:
		
		//토큰키 받기
		//java에서 url 받기
		//HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		//headers,body 만들기
		//headers 만들기
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type",content_type);
		
		//body 부분 만들기 : httpBody MultivalueMap 오브젝트 생성
		MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
		params.add("grant_type",grant_type);
		params.add("client_id",client_id);
		params.add("redirect_uri",redirect_uri);
		params.add("code",code);
		
		//HttpEntity 에 headers,MultivalueMap params 를 1개로 합치기
		HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest = new HttpEntity<>(params,headers);
		
		//카카오 서버로 url전송해서 return받기
		//매개변수 : url링크,전송방식 - post,(headers,body),String.class형태로 전송
		ResponseEntity<String> response = rt.exchange(tokenUrl,HttpMethod.POST,kakaoTokenRequest,String.class);
		
		System.out.println("kakao token 요청 응답: "+response);
		
		return "success";
	}

}
