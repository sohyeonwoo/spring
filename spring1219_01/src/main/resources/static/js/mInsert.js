/**
 * 
 */

$(function(){
	let chkKeyup =0;

//회원가입버튼 클릭
$("#saveBtn").click(function(){
	if(chkKeyup!=1){
		alert("아이디 중복확인 하셔야 다음으로 진행 가능합니다.");
		return false;
	}else{
		alert("다음으로 진행");
	}
		//ajax시작 //Ajax 돌리기
			$.ajax({
				url: "/member/mInsert",
				type: "post",
				 data:$("#memberFrm").serialize(), //form 데이터 한번에 전송
				dataType: "text",//받는 파일형태 : text,json,xml
				success: function(data) {
					alert("성공");
					console.log("data:" + data);
					
					if(data=="가입완료"){
						alert("회원가입이 완료되었습니다.");
						location.href="/index";
					}
				},
				error: function() {
					alert("실패")
				}
			});// ajax
	
	
});

//아이디 확인 버튼 클릭후 아이디가 수정되었는지 확인
$(function() {
	$("#id").keyup(function(){
		$("#chkTxt").css({"color":"black","font-weight":"700"})
		chkKeyup =0;
	});
	
	
	$("#idCheckBtn").click(function() {
		//아이디 중복 체크
		alert("아이디 중복 체크를 합니다.");
		console.log($("#id").val());
		
		//id가 있는지 체크
		if($("#id").val().length<1){
			alert("아이디를 입력하셔야 체크 가능합니다.");
			$("#id").focus();
			return false;
		}
			//ajax시작 //Ajax 돌리기
			$.ajax({
				url: "/member/idCheck",
				type: "post",
				// data:$("#memberFrm").serialize(), //form 데이터 한번에 전송
				data: { "id": $("#id").val() }, //데이터를 각각 보냄
				//contentType:"json", //내가 보내는 파일 형태 
				dataType: "text",//받는 파일형태 : text,json,xml
				success: function(data) {
					if(data=="사용가능"){
						$("#chkTxt").text("아이디 사용가능");
						$("#chkTxt").css({"color":"blue","font-weight":"700"});
					}else{
						$("#chkTxt").text("사용불가 중복");
						$("#chkTxt").css({"color":"red","font-weight":"700"});
					}
					console.log("data:" + data);
					chkKeyup=1;
				},
				error: function() {
					alert("실패")
				}
			});// ajax
});
	});
});