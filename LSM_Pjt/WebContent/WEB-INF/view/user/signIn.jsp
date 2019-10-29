<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!DOCTYPE html>
	<html lang="zxx" class="no-js">
		<%@ include file="../head/head_user.jsp" %>
		<body style="background-color: #666666;">
			
			<div class="limiter">
				<div class="container-login100">
					<div class="wrap-login100">
						
						<form class="login100-form validate-form" autocomplete="off" method="post" action="/user/signInCheck.do">
							<span class="login100-form-title p-b-43">
								로그인
							</span>
							
							
							<div class="wrap-input100 validate-input" data-validate = "Valid email is required: ex@abc.xyz">
								<input class="input100" type="text" name="user_id">
								<span class="focus-input100"></span>
								<span class="label-input100">아이디</span>
							</div>
							
							
							<div class="wrap-input100 validate-input" data-validate="Password is required">
								<input class="input100" type="password" name="password">
								<span class="focus-input100"></span>
								<span class="label-input100">비밀번호</span>
							</div>
		
							<div class="flex-sb-m w-full p-t-3 p-b-32">
								<div class="contact100-form-checkbox">
									<input class="input-checkbox100" id="ckb1" type="checkbox" name="remember-me">
									<label class="label-checkbox100" for="ckb1">
										아이디 기억하기
									</label>
								</div>
		
								<div>
									<a href="/signUp.do" class="txt1">
										회원가입하기
									</a>
									<a class="txt1">
										/
									</a>
									<a href="/findUserId.do" class="txt1">
										아이디 찾기
									</a>
									<a class="txt1">
										/
									</a>
									<a href="/findUserPw.do" class="txt1">
										비밀번호 찾기
									</a>
								</div>
							</div>
					
		
							<div class="container-login100-form-btn">
								<button type="submit" class="login100-form-btn">
									로그인
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