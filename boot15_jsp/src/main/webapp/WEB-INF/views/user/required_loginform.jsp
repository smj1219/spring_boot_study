<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/user/required_loginform.jsp</title>
</head>
<body>
	<h3>로그인이 필요 합니다</h3>
	<form action="${pageContext.request.contextPath }/user/login" method="post">
		<input type="text" name="userName" placeholder="사용자명..."/>
		<input type="password" name="password" placeholder="비밀번호..."/>
		<button type="submit">로그인</button>
	</form>
</body>
</html>