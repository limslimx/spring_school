<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
	<!DOCTYPE html>
	<html lang="zxx" class="no-js">
	<%@ include file="../head/head.jsp" %>
		<body>	
			<%@ include file="../nav/user.jsp" %>
			  
			<!-- start banner Area -->
			<!-- 백그라운드 이미지 설정 -->
			<section class="about-banner relative">
				<div class="overlay overlay-bg"></div>
				<div class="container">				
					<div class="row d-flex align-items-center justify-content-center">
						<div class="about-content col-lg-12">
							<h1 class="text-white">
								My Page				
							</h1>	
						</div>	
					</div>
				</div>
			</section>
			<!-- End banner Area -->
			<section class="hot-deal-area section-gap">
				<div class="container">					
					<div class="row justify-content-center">	
						<form action="changePwProc.do" method="post">
							
							<fieldset class="form-group position-relative has-icon-left mb-1">
								<input type="password" class="form-control form-control-lg input-lg" id="pw" name="passwd" placeholder="새 비밀번호" required style="margin-bottom:5%">
								<input type="password" class="form-control form-control-lg input-lg" id="pwConfirm" name="passwdConfirm" placeholder="새 비밀번호 확인" required style="margin-bottom:5%">
								<div id="pwConfirmMsg" style="height:1.6em"></div>
								<div class="form-control-position">
								    <i class="icon-key3"></i>
								</div>
								<div style="height:1.6em"></div>
							</fieldset>
							
							<button type="submit" id="submitBtn" class="btn btn-primary btn-lg btn-block">변경</button>
							<button type="button" id="submitBtn" onclick="location.href='/mypage.do'"class="btn btn-primary btn-lg btn-block">취소</button>
						</form>
					</div>
				</div>
			 </section>
			<%@ include file="../footer/footer_main.jsp" %>
			<%@ include file="../js/js_main.jsp" %>
			<script>
				$("#submitBtn").attr("disabled", "disabled")
				
				function validResult(id, msg, valid, focus=false){
					// 입력 항목 옆에 메시지를 출력함. 메시지가 표시되는 곳의 아이디는 입력항목의 아이디+Msg임.
					// 예) email 유효성 검사 메시시가 표시될 공간의 아이디는 emailMsg
					$("#"+id+"Msg").text(msg);
					
					// 값이 유효할 경우 포록색, 아닐 경우 빨간색으로 글씨색 표시
					$("#"+id+"Msg").attr("style", "color:"+(valid? "green":"red"));
					
					// 유효성 검사 결과를 위에서 정의한 validity 객체에 저장
					validity[id] = valid;
					
					// focus가 참일 경우 해당 입력항목으로 커서가 가게 한다
					if(focus){
						$("#"+id).focus();
					}
					
					// 입력 항목이 모두 유효한지 확인
					validCheckAll();
				}
			
				function validatePw(){
					var passwd = $("#pw").val();
					var pwdChk = $("#pwConfirm").val();
					var valid = false;
					var msg = '';
					if (passwd != pwdChk) {
						msg = "암호가 일치하지 않습니다."; 
						
					}else if (passwd == "") {
						msg = "암호를 입력해주세요.";
						
					}else{
						msg = "암호가 일치합니다.";
						valid = true;
						$("#submitBtn").removeAttr("disabled");
						
					}
					validResult("pwConfirm", msg, valid);
				}
				
				$("#pw").on('input', function(){
					validatePw();
				});
				$("#pwConfirm").on('input', function(){
					validatePw();
				});
			</script>
		</body>
	</html>