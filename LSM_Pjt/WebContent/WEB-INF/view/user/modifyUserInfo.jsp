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
						<form action="changeName.do" method="post" style="display:block">
							<p style="display:inline; font-size:24px">이름: <%=user_name %></p><br><br>
							<button type="submit" id="submitBtn" class="btn btn-primary btn-lg btn-block">변경하기</button>
						</form><hr>
						<form action="changePw.do" method="post">
							<p style="display:inline; font-size:24px">PW</p><br><br>
							<button type="submit" id="submitBtn" class="btn btn-primary btn-lg btn-block">변경하기</button>
						</form><hr>
						<form action="changeEmail.do" method="post">
							<p style="display:inline; font-size:24px">EMAIL: <%=email %></p><br><br>
							<button type="submit" id="submitBtn" class="btn btn-primary btn-lg btn-block">변경하기</button>
						</form>
					</div>
				</div>
			 </section>
			<%@ include file="../footer/footer_main.jsp" %>
			<%@ include file="../js/js_main.jsp" %>
		</body>
	</html>