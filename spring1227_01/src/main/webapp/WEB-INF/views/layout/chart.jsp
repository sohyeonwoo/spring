<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>
		<script>
			$(function(){
				$("#cbtn").click(function(){
					alert("차트 데이터를 가져옵니다.");
					alert($("#cyear").val());
					let cyear = $("#cyear").val();
	// Chart객체 리셋해야 다시 차트를 그릴수 있슴.
					 
					let chartStatus = Chart.getChart("myChart"); // <canvas> id
					 if (chartStatus != undefined) {
					 	chartStatus.destroy();
					 	console.log("mhChart리셋");
					 }
						let cLabels = [];
						let cData=[];
					
					//db연결 - 데이터를 가져옴.
					$.ajax({
						url:"/layout/incomeSelect",
						type:"post",
						data:{"cyear":cyear},
						dataType:"json",
						success: function(data){
							alert("성공");
							console.log(data);
							console.log("갯수:"+ data.length);
							console.log("1월 수입:"+ data[0].csale);
							
	//for문 돌려서 cmonth 매출액 가져오기
							for(let i=0;i<data.length;i++){
								cLabels.push(data[i].cmonth);
								cData.push(data[i].csale);
							}
	//단순 for문
							/* for(d:data){
								cLabels.push(d.cmonth);
								cData.push(d.csale);
							} */
							console.log(cLabels);
							console.log(cData);
							
	//차트 나타내기 시작
							  const ctx = document.getElementById('myChart');

							  new Chart(ctx, {
							    type: 'bar',
							    data: {
							      labels:cLabels,
							      datasets: [{
							        label: cyear+'매출액',
							        data:cData,
							        backgroundColor: [
							            'rgba(255, 99, 132, 0.2)',
							            'rgba(255, 159, 64, 0.2)',
							            'rgba(255, 205, 86, 0.2)',
							            'rgba(75, 192, 192, 0.2)',
							            'rgba(54, 162, 235, 0.2)',
							            'rgba(153, 102, 255, 0.2)',
							            'rgba(201, 203, 207, 0.2)'
							          ],
							          borderColor: [
							            'rgb(255, 99, 132)',
							            'rgb(255, 159, 64)',
							            'rgb(255, 205, 86)',
							            'rgb(75, 192, 192)',
							            'rgb(54, 162, 235)',
							            'rgb(153, 102, 255)',
							            'rgb(201, 203, 207)'
							          ],
							        
							        borderWidth: 1
							      }]
							    },
							    options: {
							      scales: {
							        y: {
							          beginAtZero: true
							        }
							      }
							    }
							  });
	//차트 나타내기 시작
							
						},
						error:function(){
							alert("실패");
						}
						
					})
					
	
				});
			});
		</script>
		<style>
			.area{width: 50%; margin:20px auto; border:3px solid black;}
		</style>

	<select id="cyear" >
		<option value="2022년">2022년</option>
		<option value="2023년">2023년</option>
	</select>
	<button id="cbtn" style="margin-top:50px;">차트데이터 가져오기</button>
	<br>
       <div class="area">
         <canvas id="myChart"></canvas>
       </div>
 <!-- footer.jsp include -->
<%@include file="../include/footer.jsp" %>