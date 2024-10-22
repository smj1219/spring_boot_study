<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/user/login_fail.jsp</title>
</head>
<body>
	<script>
		alert("아이디 혹은 비밀번호가 틀려요");
		location.href="${pageContext.request.contextPath }/user/loginform";
	</script>
</body>
</html>