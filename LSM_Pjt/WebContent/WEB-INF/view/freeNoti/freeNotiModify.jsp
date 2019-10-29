<%@page import="poly.dto.FreeNotiDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%
	FreeNotiDTO fnDTO=(FreeNotiDTO)request.getAttribute("fnDTO");
%>

	<!DOCTYPE html>
	<html lang="zxx" class="no-js">
	<head>
		
		<%@ include file="../head/head.jsp" %>	
		</head>
		<body>	
			<%@ include file="../nav/user.jsp" %>	
			  
			<!-- start banner Area -->
			<!-- 백그라운드 이미지 설정 -->
			<section class="about-banner relative">
				<div class="overlay overlay-bg"></div>
				<div>
					<div class="container">				
					<div class="row d-flex align-items-center justify-content-center"></div>
						<div class="about-content col-lg-12">
							<h1 class="text-white">
								자유 게시판				
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

						<form method="post" action="/freeNotiModifyProc.do" style="width:100%">
							<input type="hidden" name="seq" value="<%=fnDTO.getSeq() %>">
							<div style="text-algin:center;font-size:2em">글 수정</div>
							<table border="1" style="width:100%;margin-bottom:5%">
								<col width="100px">
								<col width="500px">
								<tr>
									<td align="center">제목</td>
									<td><input type="text" name="title" maxlength="100" style="width:100%" value="<%=fnDTO.getTitle() %>"></td>
								</tr>
								<tr>
									<td colspan="2">
										<textarea name="content" style="width:100%;height:400px"><%=fnDTO.getContent() %></textarea>
									</td>
								</tr>
							</table>
							<div align="center">
								<input type="submit" value="수정" class="btn btn-primary">
								<input type="button" onclick="location.href='/freeNotiDetail.do?seq=<%=fnDTO.getSeq() %>'" value="뒤로" class="btn btn-primary">
							</div>
						</form>
							
					</div>
				</div>
			</section>
			<!-- End hot-deal Area -->
			
			<%@ include file="../footer/footer_main.jsp" %>
			<%@ include file="../js/js_main.jsp" %>	
		</body>
	</html>