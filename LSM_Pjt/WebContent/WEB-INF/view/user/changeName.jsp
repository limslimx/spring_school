<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String user_name=(String)request.getAttribute("user_name");
	String email=(String)request.getAttribute("email");
%>

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
					<div class="row d-flex align-items-center justify-content-center"></div>
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
						<form action="changeNameProc.do" method="post" style="display:block">
							사용자 이름: <input type="text" name="user_name" maxlength="100" style="width:100%" placeholder="변경할 이름 입력"><br><br>
							<button type="submit" id="submitBtn" class="btn btn-primary btn-lg btn-block">변경하기</button>
						</form><hr>
					</div>
				</div>
			 </section>
			<%@ include file="../footer/footer_main.jsp" %>
			<%@ include file="../js/js_main.jsp" %>
		</body>
	</html>