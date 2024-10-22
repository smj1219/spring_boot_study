<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/user/login_success.jsp</title>
</head>
<body>
	<p>로그인 되었습니다</p>
	<a href="${pageContext.request.contextPath }/">인덱스로</a>
</body>
</html>