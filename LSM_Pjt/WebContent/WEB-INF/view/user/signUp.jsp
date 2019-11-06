<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../head/head_user.jsp" %>
		<body style="background-color: #666666;">
			
			<div class="limiter">
				<div class="container-login100">
					<div class="wrap-login100">
						<form class="login100-form validate-form form-wrap" autocomplete="off" method="post" action="/userRegProc.do" onsubmit="return validity['id']&&validity['email']&&validity['pwConfirm'];">
							<span class="login100-form-title p-b-43">
								회원가입
							</span>
							
							<div class="wrap-input100 validate-input" data-validate = "Valid is required: ex@abc.xyz">
								<input class="input100" type="text" name="user_name" id="user_name">
								<span class="focus-input100"></span>
								<span class="label-input100">이름</span>
							</div>
							<br>
							
							<div class="wrap-input100 validate-input" data-validate = "Valid email is required: ex@abc.xyz">
								<input class="input100" type="text" id="id" name="id">
								<span class="focus-input100"></span>
								<span class="label-input100">아이디</span>
							</div>
							<div style="color: red; height:1.6em;" id="idMsg"></div>
							
							<div class="wrap-input100 validate-input" data-validate = "Valid email is required: ex@abc.xyz">
								<input class="input100" type="text" id="email" name="email">
								<span class="focus-input100"></span>
								<span class="label-input100">이메일</span>
							</div>
							<div id="emailMsg" style="height:1.6em"></div>
							
							<div class="wrap-input100 validate-input" data-validate="Password is required">
								<input class="input100" type="password" name="pw" id="pw">
								<span class="focus-input100"></span>
								<span class="label-input100">비밀번호</span>
							</div>
							
							<div class="wrap-input100 validate-input" data-validate="Password is required">
								<input class="input100" type="password" name="pwConfirm" id="pwConfirm">
								<span class="focus-input100"></span>
								<span class="label-input100">비밀번호 확인</span>
							</div>
							<div id="pwConfirmMsg" style="height:1.6em"></div>
		
							<div class="flex-sb-m w-full p-t-3 p-b-32">
								<div>
									<a href="/signIn.do" class="txt1">
										로그인하기
									</a>
									
								</div>
								
							</div>
					
		
							<div class="container-login100-form-btn">
								<button type="submit" disabled="disabled" id="submitBtn" class="login100-form-btn">
									회원가입
								</button>
								
							</div>
							
						</form>
		
						<div class="login100-more" style="background-image: url('/resources/images/soccer.jpg');">
						</div>
					</div>
				</div>
			</div>
	<%@ include file="../js/js_user.jsp" %>
	<%@ include file="../js/validate.jsp" %>
		</body>
</html>