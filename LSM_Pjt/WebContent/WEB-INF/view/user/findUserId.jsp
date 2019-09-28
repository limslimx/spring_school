<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="findUserIdProc.do" method="post">
	<table border="1">
		<col width="150px">
		<col width="150px">
		<col width="150px">
		<col width="150px">
		<tr>
			<td>이름</td>
			<td><input type="text" name="user_name" style="width:150px" placeholder="이름"/></td>
		</tr>
	</table>
	<input type="submit" value="Id 찾기">
</form>
</body>
</html>