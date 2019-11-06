<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
						<div style="font-size:2em">글 작성</div>
						 <form action="/freeNotiRegProc.do" method="post" style="width:100%">
								<input type="hidden" name="seq" />
									<div>
							       		<h3><input type="text" name="title" placeholder="제목"/></h3>
							       		<hr>
									    <div>
									    	<p><textarea name="content" style="width: 100%; height: 500px" placeholder="내용"></textarea></p>
									    </div>
									    <div>
									        <input type="submit" value="등록" class="btn btn-primary" id="submitBtn"/>
									         <input type="button" onclick="location.href='/freeNotiList.do'" value="취소" class="btn btn-primary">
									    </div>
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