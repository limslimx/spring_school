<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메일 전송 폼</title>
</head>
<body>
<form action="/mail/sendMail.do" method="post">
	<div>
		<input type="text" name="toMail" size="120" style="width:100%" placeholder="상대 이메일">
	</div>
	<div align="center">
		<input type="text" name="title" size="120" style="width:100%" placeholder="제목">
	</div>
	<p>
	<div align="center">
		<textarea name="contents" rows="12" cols="120" style="width:100%; resize: none" placeholder="내용"></textarea>
	</div>
	<p>
	<div align="center">
		<input type="submit" value="메일 전송">
	</div>
</form>
</body>
</html>