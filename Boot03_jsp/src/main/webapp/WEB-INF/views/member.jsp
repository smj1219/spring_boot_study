<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>webapp/WEB-INF/views/member.jsp</title>
</head>
<body>
	<div class="container">
		<p>
			번호 : <strong>${requestScope.dto.num }</strong>
			이름 : <strong>${dto.name }</strong>
			주소 : <strong>${dto.addr }</strong>
		</p>
	</div>
</body>
</html>