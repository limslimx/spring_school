<%@page import="poly.dto.FreeNotiDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%> 
    
<%
	List<FreeNotiDTO> fnList=(List<FreeNotiDTO>)request.getAttribute("fnList");
	String id=(String)session.getAttribute("user_id");
%>

	<!DOCTYPE html>
	<html lang="zxx" class="no-js">
	<head>
		<%@ include file="../head/head.jsp" %>	
		</head>
		<body>
		<script>
		function deleteBtn(){
			
			if(<%=id %>!=null){
				document.getElementById("reg").style.visibility="visible";
			}
			
		}
		</script>	
			<%@ include file="../nav/user.jsp" %>
			  
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
										for(FreeNotiDTO fnDTO:fnList){
									%>
										
										<tr>
											<td><%=fnDTO.getSeq() %></td>
											<td><a href="/freeNotiDetail.do?seq=<%=fnDTO.getSeq() %>"><%=fnDTO.getTitle() %></a></td>
											<td><%=fnDTO.getReg_dt() %></td>
											<td><%=fnDTO.getReg_id() %></td>
										</tr>
									<%} %>
									</tbody>
								</table> 
								<hr>
								<a href="/freeNotiReg.do" class="btn btn-primary" id="reg" style="visibility: hidden">글작성</a>		
						</div>			
					</div>
				</div>	
			</section>
			<!-- End hot-deal Area -->
			
			<%@ include file="../footer/footer_main.jsp" %>
			<%@ include file="../js/js_main.jsp" %>
		</body>
	</html>