<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
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
				           <li><a href="/userInfoList.do">회원정보 관리</a></li>
				          <li><a href="#">축구일정 관리</a></li>
				          <li><a href="#">게시판 관리</a>
				          	<ul>
				          		<li><a href="/notiInfoList.do">공지사항게시판 관리</a>
				          		<li><a href="#">자유게시판 관리</a>
				          	</ul>
				          </li>
						  <li><a href="#">관리자님 환영합니다!</a>
						  <ul>
						  	<li><a href="/logout.do">Log Out</a></li>
						  </ul>
						  </li>
				        </ul>
				      </nav><!-- #nav-menu-container -->					      		  
					</div>
				</div>
			</header><!-- #header -->
</body>
</html>