<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/user/info.jsp</title>
<style>
	#profileImage{
		width: 100px;
		height: 100px;
		border: 1px solid #cecece;
		border-radius: 50%;
	}
</style>
</head>
<body>
	<div class="container">
		<a href="${pageContext.request.contextPath }/user/updateform">개인 정보 수정</a>
		<h1>가입 정보 입니다</h1>
		<table>
			<tr>
				<th>사용자명</th>
				<td>${dto.userName}</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<a href="${pageContext.request.contextPath }/user/pwd_updateform">수정하기</a>
				</td>
			</tr>
			<tr>
				<th>프로필 이미지</th>
				<td>
					<c:choose>
						<c:when test="${ empty dto.profile }">
							<svg xmlns="http://www.w3.org/2000/svg" width="100" height="100" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
								<path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
								<path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
							</svg>
						</c:when>
						<c:otherwise>
							<img id="profileImage" src="${pageContext.request.contextPath }/upload/images/${dto.profile }" alt="프로필 이미지" />
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>${dto.email}</td>
			</tr>
			<tr>
				<th>가입일</th>
				<td>${dto.regdate }</td>
			</tr>
		</table>
	</div>
</body>
</html>


