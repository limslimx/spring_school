<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String user_id=(String)session.getAttribute("user_id");
%>

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
				          <li><a href="/soccer/schedule.do">Schedule</a></li>
				          <li><a href="/noti/notiList.do">Notice Board</a>
				          	<ul>
				          		<li><a href="/noti/notiList.do">공지사항게시판</a>
				          		<li><a href="/freeNotiList.do">자유게시판</a>
				          	</ul>
				          </li>
						  <li><a href="/contactForm.do">Contact</a></li>
						  <li><a href="#"><%=user_id %>님의 My Page</a>
						  <ul>
						  	<li><a href="/checkPw.do">My Page</a></li>
						  	<li><a href="/logout.do">Log Out</a></li>
						  </ul>
						  </li>
				        </ul>
				      </nav><!-- #nav-menu-container -->					      		  
					</div>
				</div>
			</header><!-- #header -->
