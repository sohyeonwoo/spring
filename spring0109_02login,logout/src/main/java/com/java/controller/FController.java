package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.dto.KakaoDto;
import com.java.dto.LogoutDto;
import com.java.dto.TokenDto;

import jakarta.servlet.http.HttpSession;

@Controller
public class FController {
	@Autowired
	HttpSession session;
	
	@GetMapping("/")
	public String main() {
		return "main";
	}
	
	@GetMapping("logout")
	public String logout(Model model) {
		System.out.println("logout session_token : "+session.getAttribute("session_token"));
	//로그아웃 토큰키 전송
		String logoutUrl = "https://kapi.kakao.com/v1/user/logout";
		
	// header부분 보내기
		String Content_type="application/x-www-form-urlencoded;charset=utf-8";
		String Authorization ="Bearer "+session.getAttribute("session_token");
		
		//url전송
		RestTemplate rt = new RestTemplate();
		
		//headers 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", Content_type);
		headers.add("Authorization", Authorization);
		
		//header
		HttpEntity<MultiValueMap<String,String>> logoutRequest = new HttpEntity<>(headers);
		
		//url,전송방식,데이터 // String 타입
		ResponseEntity<String> response =  rt.exchange(logoutUrl,HttpMethod.POST,logoutRequest,String.class);
		System.out.println("요청값 token response : "+ response);
		System.out.println("요청값 token response getBody : "+ response.getBody());
		
	
		//json 데이터를 java 객체로 변경
		ObjectMapper objectMapper = new ObjectMapper();
		//tokenDto 객체
		LogoutDto logoutDto = null;
		try {
			logoutDto = objectMapper.readValue(response.getBody(),LogoutDto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("로그아웃값 id : "+ logoutDto.getId());
		
		//model로 결과값 전송
		model.addAttribute("result",response.getBody());
		//session종료
		session.invalidate();
		
		return "logout";
	}
	
	@GetMapping("login")
	public String login() {
		return "login";
	}
	
	
	
//카카오로그인 코드
	//@ResponseBody
	@RequestMapping("kakao/oauth")
	public String oauth(String code) {
		//1.code값 리턴
		System.out.println("kakao code: "+ code);
		//2.token키 받기
		String tokenUrl = "https://kauth.kakao.com/oauth/token";
		// header부분 보내기
		String Content_type="application/x-www-form-urlencoded;charset=utf-8";
		// body 부분 보내기
		String grant_type="authorization_code";
		String client_id ="6aead5273c950ac558cb4231e333276b";
		String redirect_uri ="http://localhost:8000/kakao/oauth";
		code=code;
		//url전송
		RestTemplate rt = new RestTemplate();
		//headers 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", Content_type);
		//body 생성
		MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", grant_type);
		params.add("client_id", client_id);
		params.add("redirect_uri", redirect_uri);
		params.add("code", code);
		
		//header,body합치기
		HttpEntity<MultiValueMap<String,String>> tokenRequest = new HttpEntity<>(params,headers);
		//url,전송방식,데이터 // String 타입
		ResponseEntity<String> response =  rt.exchange(tokenUrl,HttpMethod.POST,tokenRequest,String.class);
		System.out.println("요청값 token response : "+ response);
		System.out.println("요청값 token response getBody : "+ response.getBody());
		//json 데이터를 java 객체로 변경
		ObjectMapper objectMapper = new ObjectMapper();
		TokenDto tokenDto = null;
		try {
			tokenDto = objectMapper.readValue(response.getBody(), TokenDto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("tokenDto access_token : "+tokenDto.getAccess_token());
		
	//토큰키를 전송해서 회원정보값 받기
		//2.token키 받기
		String tokenUrl_user = "https://kapi.kakao.com/v2/user/me";
		// header부분 보내기
	    Content_type="application/x-www-form-urlencoded;charset=utf-8";
		String Authorization ="Bearer "+tokenDto.getAccess_token();
		//url전송
		RestTemplate rt_user = new RestTemplate();
		//headers 생성
		HttpHeaders headers_user = new HttpHeaders();
		headers_user.add("Content-type", Content_type);
		headers_user.add("Authorization", Authorization);
		
		//header,body합치기
		HttpEntity<MultiValueMap<String,String>> tokenRequest_user = new HttpEntity<>(headers_user);
		
	//url,전송방식,데이터 // String 타입
		ResponseEntity<String> response_user =  rt.exchange(tokenUrl_user,HttpMethod.POST,tokenRequest_user,String.class);
		System.out.println("response_user :"+response_user.getBody());
		
	//json 데이터를 java 객체로 변경
		ObjectMapper objectMapper_user = new ObjectMapper();
//tokenDto 객체
		KakaoDto kakaoDto = null;
		try {
			kakaoDto = objectMapper_user.readValue(response_user.getBody(),KakaoDto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("토큰키값 kakao id : "+ kakaoDto.getId());
		
		String targetUrl="";
		if(kakaoDto !=null) {
			System.out.println("카카오 로그인이 완료되었습니다.");
			session.setAttribute("session_id",kakaoDto.getId());
			session.setAttribute("session_name",kakaoDto.getProperties().getNickname());
			session.setAttribute("session_token",tokenDto.getAccess_token());
			targetUrl="redirect:/";
		}else {
			System.out.println("카카오 로그인 에러입니다.");
			targetUrl="login";
		}
		
		
		return targetUrl;
	}

}
