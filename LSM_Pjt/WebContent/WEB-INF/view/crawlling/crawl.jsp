<%@page import="poly.util.CmmUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String res=CmmUtil.nvl((String)request.getAttribute("res"), "0");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>크롤링 테스트 페이지</title>
</head>
<body>
<%=res %>개의 정보가 크롤링되었습니다.
</body>
</html>