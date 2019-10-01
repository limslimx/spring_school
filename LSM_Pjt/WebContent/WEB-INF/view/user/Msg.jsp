<%@page import="poly.util.CmmUtil"%>
<%@page import="poly.dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    	String msg=CmmUtil.nvl((String)request.getAttribute("msg"));
    	
    	UserDTO pDTO=(UserDTO)request.getAttribute("pDTO");
    	
    	if(pDTO==null){
    		pDTO=new UserDTO();
    	}
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입을 축하드립니다.</title>
<script type="text/javascript">
	alert("<%=msg%>");
</script>
</head>
<body>
<%=CmmUtil.nvl(pDTO.getUser_name()) %>님의 회원가입을 축하드립니다.
</body>
</html>