<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/file/list.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
    <div class="container">        
        <h1>자료실 목록 입니다</h1>
        <a href="${pageContext.request.contextPath }/file/upload_form">새자료 올리기</a>
        <table class="table table-striped">
            <thead class="table-dark">
                <tr>
                    <th>번호</th>
                    <th>작성자</th>
                    <th>제목</th>
                    <th>파일명</th>
                    <th>등록일</th>
                    <th>삭제</th>
                </tr>
            </thead>
            <tbody>    
            	<c:forEach var="tmp" items="${list }">
            		<tr>
	            		<td>${tmp.num }</td>
	            		<td>${tmp.writer }</td>
	            		<td>${tmp.title }</td>
	            		<td>
	            			<a href="${pageContext.request.contextPath }/file/download?num=${tmp.num }">${tmp.orgFileName }</a>
	            		</td>
	            		<td>${tmp.regdate }</td>
	            		<td>
	            			<c:if test="${userName eq tmp.writer }">
	            				<a href="${pageContext.request.contextPath }/file/delete?num=${tmp.num }">삭제</a>
	            			</c:if>
	            		</td>
	            	</tr>
            	</c:forEach>
            </tbody>
        </table>
        <nav>
        	<c:if test="${totalPageCount ne 0 }">
        		<ul class="pagination">
        			<c:if test="${startPageNum != 1 }">
        				<li class="page-item">
        					<a class="page-link" href="${pageContext.request.contextPath }/file/list?pageNum=${startPageNum-1 }&condition=${dto.condition}&keyword=${dto.keyword}">Prev</a>
        				</li>
        			</c:if>
        			<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
        				<li class="page-item ${pageNum eq i ? 'active' : '' }">
        					<a class="page-link" href="${pageContext.request.contextPath }/file/list?pageNum=${i }&condition=${dto.condition}&keyword=${dto.keyword}">${i }</a>
        				</li>
        			</c:forEach>
        			<c:if test="${endPageNum < totalPageCount }">
        				<li class="page-item">
        					<a class="page-link" href="${pageContext.request.contextPath }/file/list?pageNum=${endPageNum+1}&condition=${dto.condition}&keyword=${dto.keyword}">Next</a>
        				</li>
        			</c:if>
        		</ul>
        	</c:if>
        </nav>
        <form action="${pageContext.request.contextPath }/file/list" method="get">
        	<label for="condition">검색조건</label>
        	<select name="condition" id="condition">
        		<option value="title_filename" ${dto.condition eq 'title_filename' ? 'selected' : ''}>제목 + 파일명</option>
        		<option value="title" ${dto.condition eq 'title' ? 'selected' : ''}>제목</option>
        		<option value="writer" ${dto.condition eq 'writer' ? 'selected' : ''}>작성자</option>
        	</select>
        	<input type="text" name="keyword" placeholder="검색어..." value="${dto.keyword }"/>
        	<button class="btn btn-primary btn-sm" type="submit">검색</button>
        	<a class="btn btn-success btn-sm" href="${pageContext.request.contextPath }/file/list">새로고침</a>
        </form>
        <%-- 
        	not empty 는 어떤값이 비어 있지 않은지 여부를 알수 있다.
        	empty 연산자는 비어 있으면 true 를 얻어낸다.
        	빈문자열 or null 은 비어 있다고 판정된다. 
         --%>
        <c:if test="${not empty dto.keyword }">
        	<p>
        		<strong>${totalRow }</strong> 개의 자료가 검색 되었습니다.
        	</p>
        </c:if>
    </div>
</body>
</html>


