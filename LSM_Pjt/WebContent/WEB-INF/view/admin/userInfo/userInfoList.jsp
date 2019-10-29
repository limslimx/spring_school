<%@page import="poly.dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%> 
    
<%
	List<UserDTO> uList=(List<UserDTO>)request.getAttribute("uList");
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
								회원정보 관리				
							</h1>
						</div>	
				</div>
			</section>
			<!-- End banner Area -->	

			<!-- Start hot-deal Area -->
			<section class="hot-deal-area section-gap">
				<div class="container">					
					<div class="row justify-content-center">

							<div style="width:100%; margin-left:5%">
								
								<table style="width:100%">
									<thead>
										<tr>
											<th width="100px">ID</th>
											<th width="500px">이름</th>
										</tr>
									</thead><hr>
									<tbody>
									<%
										for(UserDTO uDTO:uList){
											if(uDTO.getUser_id().equals("admin")){
												continue;
											}
									%>
										<tr>
											<td><a href="/userInfoDetail.do?user_id=<%=uDTO.getUser_id() %>"><%=uDTO.getUser_id() %></a></td>
											<td><a href="/userInfoDetail.do?user_id=<%=uDTO.getUser_id() %>"><%=uDTO.getUser_name() %></a></td>
										</tr>
									
									<%} %>
									</tbody>
								</table> 
								<hr>
						</div>			
					</div>
				</div>	
			</section>
			<!-- End hot-deal Area -->
			
			<%@ include file="../../footer/footer_main.jsp" %>
			<%@ include file="../../js/js_main.jsp" %>
		</body>
	</html>