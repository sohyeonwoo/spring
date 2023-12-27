/**
 * 
 */


 $(function(){
	 $(".btn_login").click(function(){
		 if($("#id").val().length<1){
			 alert("아이디 입력")
			 $("#id").focus();
			 return false;
		 }
		 loginFrm.submit();
	 })
	 
 });
 