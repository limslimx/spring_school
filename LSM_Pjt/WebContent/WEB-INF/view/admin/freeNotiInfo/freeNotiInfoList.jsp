<%@page import="poly.dto.NotiDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%> 
    
<%
	List<NotiDTO> nList=(List<NotiDTO>)request.getAttribute("nList");
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
								자유 게시판				
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
											<th width="100px">글번호</th>
											<th width="500px">글제목</th>
											<th width="200px">날짜</th>
											<th width="200px">작성자</th>
										</tr>
									</thead><hr>
									<tbody>
									<%
										for(NotiDTO nDTO:nList){
									%>
										
										<tr>
											<td><%=nDTO.getSeq() %></td>
											<td><a href="/notiInfoDetail.do?seq=<%=nDTO.getSeq() %>"><%=nDTO.getTitle() %></a></td>
											<td><%=nDTO.getReg_dt() %></td>
											<td><%=nDTO.getReg_id() %></td>
										</tr>
									<%} %>
									</tbody>
								</table> 
								<hr>
								<a href="/notiInfoReg.do" class="btn btn-primary">글작성</a>		
						</div>			
					</div>
				</div>	
			</section>
			<!-- End hot-deal Area -->
			
			<%@ include file="../../footer/footer_main.jsp" %>
			<%@ include file="../../js/js_main.jsp" %>
		</body>
	</html>