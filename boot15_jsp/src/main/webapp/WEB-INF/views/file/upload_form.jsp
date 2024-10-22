<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/file/upload_form.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<style>
	.drop-zone{
		height: 100px;
		border: 3px solid #cecece;
		border-radius: 10px;
		display: flex;
		justify-content: center; /* 좌우로 가운데 정렬 */
		align-items: center; /* 위아래로 가운데 정렬 */
		cursor:pointer;
	}
	#myFile{
		display:none;
	}
</style>
</head>
<body>
	<div class="container">
		<h3>파일 업로드 폼 입니다.</h3>
		<form action="${pageContext.request.contextPath }/file/upload" method="post" enctype="multipart/form-data">
			<div class="mb-2">
				<label class="form-label" for="title">제목</label>
				<input class="form-control" type="text" name="title" id="title"/>
			</div>
			<div class="mb-2">
				<label for="myFile">첨부파일</label>
				<input type="file" name="myFile" id="myFile"/>
			</div>
			<div class="drop-zone mb-2">Drag and Drop! or Click Here!</div>
			<button class="btn btn-primary" type="submit">업로드</button>
		</form>
	</div>	
	<script>
		//.drop-zone div 의 참조값
		const dropZone=document.querySelector(".drop-zone");
		//dragover 되었을때 기본 이벤트 막기
		dropZone.addEventListener("dragover", (e)=>{
			e.preventDefault(); //기본 이벤트 막기
		});
		//drop 되었을때 이벤트 처리
		dropZone.addEventListener("drop", (e)=>{
			e.preventDefault();
			
			//drop 된 파일의 정보가 들어 있는 배열얻어내기
			const files=e.dataTransfer.files;
			
			//만일 하나 이상 선택했다면 
			if(files.length > 1){
				alert("하나의 파일만 drag drop 하세요!");
				return; //함수를 여기서 끝내기 
			}
			
			//선택한 파일의 정보를 콘솔에 출력해 보기
			const file = files[0];
			console.log(file);
			
			//input type="file" 에 drop 된 파일 넣어주기
			document.querySelector("#myFile").files=files;
			
			//선택된 파일명 출력하기
			dropZone.innerText=file.name;
			
		});
		
		// .drop-zone 을 클릭했을때 input type="file" 을 강제 클릭해 준다 
		dropZone.addEventListener("click", ()=>{
			document.querySelector("#myFile").click();
		});
		
		//파일을 선택했을때 change 이벤트가 일어난다
		document.querySelector("#myFile").addEventListener("change", (e)=>{
			//선택된 파일 배열 객체를 얻어낸다
			const files=e.target.files;
			//선택한 파일 
			const file=files[0];
			// .drop-zone 에 선택한 파일의 이름을 출력한다.
			dropZone.innerText=file.name;
		});
	</script>
</body>
</html>