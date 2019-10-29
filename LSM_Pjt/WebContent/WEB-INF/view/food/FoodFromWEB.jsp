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
<title>CGV 영화 수집 결과</title>
</head>
<body>
서울강서캠퍼스 식단 홈페이지에서 <%=res %>개의 식단 정보가 수집되었습니다.
</body>
</html>