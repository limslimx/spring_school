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
						<form action="checkPwProc.do" method="post">
							
							<fieldset class="form-group position-relative has-icon-left mb-1">
								<input type="password" class="form-control form-control-lg input-lg" id="pw" name="passwd" placeholder="비밀번호 입력" required style="margin-bottom:5%">
								<div class="form-control-position">
								    <i class="icon-key3"></i>
								</div>
								<div style="height:1.6em"></div>
							</fieldset>
							
							<button type="submit" id="submitBtn" class="btn btn-primary btn-lg btn-block">확인</button>
							
						</form>
					</div>
				</div>
			 </section>
			<%@ include file="../footer/footer_main.jsp" %>
			<%@ include file="../js/js_main.jsp" %>
</body>
</html>