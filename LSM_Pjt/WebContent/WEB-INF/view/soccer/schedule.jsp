<%@page import="poly.dto.ResultDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<ResultDTO> rList=(List<ResultDTO>)request.getAttribute("rList");
	String id=(String)session.getAttribute("user_id");
%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../head/head.jsp" %>
</head>
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
								프리미어리그 순위				
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
											<th width="100px">순위</th>
											<th width="700px">팀</th>
											<th width="100px">P</th>
											<th width="100px">W</th>
											<th width="100px">D</th>
											<th width="100px">L</th>
											<th width="100px">득점</th>
											<th width="100px">실점</th>
											<th width="100px">득실</th>
											<th width="100px">승점</th>
											<th width="500px">최근 경기</th>
										</tr>
									</thead><hr>
									<tbody>
									<%
										for(ResultDTO rDTO:rList){
									%>
										
										<tr>
											<td><%=rDTO.getRank() %></td>
											<td><img src=""><%=rDTO.getTeam() %></td>
											<td><%=rDTO.getTotal_match() %></td>
											<td><%=rDTO.getWon_match() %></td>
											<td><%=rDTO.getDraw_match() %></td>
											<td><%=rDTO.getLost_match() %></td>
											<td><%=rDTO.getGoal() %></td>
											<td><%=rDTO.getLost() %></td>
											<td><%=rDTO.getDiff() %></td>
											<td><%=rDTO.getPoint() %></td>
											<td><%=rDTO.getMatch() %></td>
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
			
			<%@ include file="../footer/footer_main.jsp" %>
			<%@ include file="../js/js_main.jsp" %>
</body>
</html>