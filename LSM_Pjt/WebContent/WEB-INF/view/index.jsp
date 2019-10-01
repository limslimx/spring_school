<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String id=(String)session.getAttribute("SS_USER_ID");
%>
	<!DOCTYPE html>
	<html lang="zxx" class="no-js">
<%@ include file="head.jsp" %>	
		
		<body>
		id=<%=id %>
		<script type="text/javascript">
			var id=<%=id %>
			
			document.write(id);
			
		</script>
		<script type="text/javascript">
			function doLoginUserCheck(f){
				
				if(f.user_id.value==""){
					alert("아이디를 입력하세요");
					f.user_id.focus();
					return false;
				}
				
				if(f.password.value==""){
					alert("비밀번호를 입력하세요");
					f.password.focus();
					return false;
				}
			}
		</script>
		<script type="text/javascript">
		function doRegUserCheck(f2){
			
			if(f2.user_name.value==""){
				alert("이름을 입력하세요.");
				f2.user_name.focus();
				return false;
			}
			
			if(f2.user_id.value==""){
				alert("아이디를 입력하세요.");
				f2.user_id.focus();
				return false;
			}
			
			if(f2.password.value==""){
				alert("비밀번호를 입력하세요.");
				f2.password.focus();
				return false;
			}
			
			if(f2.password2.value==""){
				alert("비밀번호확인을 입력하세요.");
				f2.password2.focus();
				return false;
			}
			
			if(f2.email.value==""){
				alert("이메일을 입력하세요.");
				f2.email.focus();
				return false;
			}
		}
	</script>
		
			<header id="header">
				<div class="header-top">
					<div class="container">
			  		<div class="row align-items-center">
			  			

			  		</div>			  					
					</div>
				</div>
				<div class="container main-menu">
					<div class="row align-items-center justify-content-between d-flex">
				      <div id="logo">
				        <a href="/index.do"><img src="img/logo.png" alt="" title="" /></a>
				      </div>
				      <nav id="nav-menu-container">
				        <ul class="nav-menu">
				          <li><a href="/index.do">Home</a></li>
				          <li><a href="schedule.do">Schedule</a></li>
				          <li><a href="/noti/notiList.do">Notice Board</a></li>
						  <li><a href="contact.do">Contact</a></li>
						  <li><a href="/user/signIn.do">Sign In</a></li>
						  <li><a href="/user/signUp.do">Sign Up</a></li>
						
				        </ul>
				      </nav><!-- #nav-menu-container -->					      		  
					</div>
				</div>
			</header><!-- #header -->
			
			<!-- start banner Area -->
			<!-- home 백그라운드 이미지 설정 -->
			<section class="banner-area relative">
				<div class="overlay overlay-bg"></div>			
				<div class="container">
					<div class="row fullscreen align-items-center justify-content-between">
						<div class="col-lg-6 col-md-6 banner-left">
							<h1 class="text-white">Welcome To Socsche</h1>
							<p class="text-white">
								This is WebPage about soccer schedule
							</p>
						</div>
						<div class="col-lg-4 col-md-6 banner-right">
							<ul class="nav nav-tabs" id="myTab" role="tablist">
							  <li class="nav-item">
							    <a class="nav-link active" id="flight-tab" data-toggle="tab" href="#flight" role="tab" aria-controls="flight" aria-selected="true">Sign In</a>
							  </li>
							  <li class="nav-item">
							    <a class="nav-link" id="hotel-tab" data-toggle="tab" href="#hotel" role="tab" aria-controls="hotel" aria-selected="false">Sign Up</a>
							  </li>
							</ul>
							<div class="tab-content" id="myTabContent">
							  <div class="tab-pane fade show active" id="flight" role="tabpanel" aria-labelledby="flight-tab">
								<form id="test" name="f" action="/user/signInCheck.do"  method="post" onsubmit="return doLoginUserCheck(this);" class="form-wrap">
									<input type="text" class="form-control" name="user_id" placeholder="아이디" onfocus="this.placeholder = ''" onblur="this.placeholder = '아이디'">									
									<input type="password" class="form-control" name="password" placeholder="비번" onfocus="this.placeholder = ''" onblur="this.placeholder = '비번'">						
									<input type="submit" value="로그인" class="primary-btn text-uppercase"></a>									
								</form>
							  </div>
							   <div class="tab-pane fade" id="hotel" role="tabpanel" aria-labelledby="hotel-tab">
								<form name="f2" action="/user/insertUserInfo.do" method="post" onsubmit="return doRegUserCheck(this);" class="form-wrap">
									<input type="text" class="form-control" name="user_name" placeholder="이름" onfocus="this.placeholder = ''" onblur="this.placeholder = '이름'">									
									<input type="text" class="form-control" name="user_id" placeholder="아이디" onfocus="this.placeholder = ''" onblur="this.placeholder = '아이디'">
									<input type="text" class="form-control" name="password" placeholder="비번" onfocus="this.placeholder = ''" onblur="this.placeholder = '비번'">
									<input type="text" class="form-control" name="password2" placeholder="비번 확인" onfocus="this.placeholder = ''" onblur="this.placeholder = '비번 확인'">
									<input type="text" class="form-control" name="email" placeholder="이메일" onfocus="this.placeholder = ''" onblur="this.placeholder = '이메일'">					
									<input type="submit" value="회원가입" class="primary-btn text-uppercase"></a>									
								</form>							  	
							   </div>
							</div>
						</div>
					</div>
				</div>					
			</section>
			<!-- End banner Area -->
s
			<%@ include file="footer.jsp" %>	
		</body>
	</html>