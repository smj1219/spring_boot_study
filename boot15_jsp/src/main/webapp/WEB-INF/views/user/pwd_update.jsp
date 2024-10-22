<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/user/pwd_update.jsp</title>
</head>
<body>
	<div class="container">
		<p>
			비밀 번호를 수정하고 로그 아웃되었습니다.
			<a href="${pageContext.request.contextPath }/user/loginform">다시 로그인 하러 가기</a>
			<a href="${pageContext.request.contextPath }/">인덱스</a>
		</p>
	</div>
</body>
</html>