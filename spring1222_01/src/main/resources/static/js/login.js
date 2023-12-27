/**
 * 
 */

    $(function(){
		$("#saveBtn").click(function(){
		if($("#id").val().length<1){
			alert("아이디입력 ");
			
			$("#id").focus();
			return false;
		}
		loginFrm.submit();
		})
	})