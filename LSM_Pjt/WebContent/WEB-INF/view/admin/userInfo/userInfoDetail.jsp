<%@page import="poly.util.EncryptUtil"%>
<%@page import="poly.dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%
	UserDTO uDTO=(UserDTO)request.getAttribute("uDTO");
%>

	<!DOCTYPE html>
	<html lang="zxx" class="no-js">
	<head>
	<%@ include file="../../head/head.jsp" %>	
		</head>
		<body>	
			<%@ include file="../../nav/admin.jsp" %>
			  
			<!-- start banner Area -->
			<!-- 백그라운드 이미지 설정 -->
			<section class="about-banner relative">
				<div class="overlay overlay-bg"></div>
				<div class="container">				
					<div class="row d-flex align-items-center justify-content-center"></div>
						<div class="about-content col-lg-12">
							<h1 class="text-white">
								게시판				
							</h1>	
						</div>	
					</div>
				</div>
			</section>
			<!-- End banner Area -->	

			<!-- Start hot-deal Area -->
			<section class="hot-deal-area section-gap">
				<div class="container">					
					<div class="row justify-content-center">
						<form method="post" action="/userInfoDelete.do" style="width:30%">
							<input type="hidden" name="user_id" value="<%=uDTO.getUser_id() %>">
							<fieldset class="form-group position-relative has-icon-left mb-1">
								ID <input type="text" class="form-control form-control-lg input-lg" id="id" name="user_id" readonly="readonly" value="<%=uDTO.getUser_id() %>" required style="margin-bottom:5%">
								이름 <input type="text" class="form-control form-control-lg input-lg" id="name" name="user_name" readonly="readonly" value="<%=uDTO.getUser_name() %>" required style="margin-bottom:5%">
								이메일 <input type="text" class="form-control form-control-lg input-lg" id="email" name="email" readonly="readonly" value="<%=EncryptUtil.decAES128CBC(uDTO.getEmail()) %>" required style="margin-bottom:5%">
								<div class="form-control-position">
								    <i class="icon-key3"></i>
								</div>
								<div align="center">
									<input type="submit" value="삭제" id="delete" class="btn btn-primary">
									<input type="button" onclick="location.href='/userInfoList.do'" value="뒤로" class="btn btn-primary">
								</div>
							</fieldset>
						</form>
						
							
					</div>
				</div>
			</section>
			<!-- End hot-deal Area -->
	
			<%@ include file="../../footer/footer_main.jsp" %>
			<%@ include file="../../js/js_main.jsp" %>	
		</body>
	</html>