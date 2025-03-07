<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/user/updateform.jsp</title>
<style>
	#profileImage{
		width: 100px;
		height: 100px;
		border: 1px solid #cecece;
		border-radius: 50%;
	}
	#image{
		display: none;
	}
</style>
</head>
<body>
	<div class="container">
		<h3>회원 정보 수정 양식</h3>
		<form action="${pageContext.request.contextPath }/user/update" method="post" enctype="multipart/form-data">
			<div>
				<label for="userName">사용자명</label>
				<input type="text" id="userName" value="${dto.userName}" readonly/>
			</div>
			<div>
				<label for="email">이메일</label>
				<input type="text" id="email" name="email" value="${dto.email}"/>
			</div>
			<div>
				<label>프로필 이미지 (아래 이미지를 클릭하거나 이미지를 drop 하세요)</label>
				<div id="dropZone">
					<a href="javascript:" id="profileLink">
						<c:choose>
							<c:when test="${empty dto.profile }">
								<svg xmlns="http://www.w3.org/2000/svg" width="100" height="100" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
									<path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
									<path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
								</svg>
							</c:when>
							<c:otherwise>
								<img id="profileImage" src="${pageContext.request.contextPath }/upload/images/${dto.profile}" alt="프로필 이미지" />
							</c:otherwise>
						</c:choose>
					</a>
				</div>
			</div>
			<button type="submit">수정확인</button>
			<button type="reset">취소</button>
			<input type="file" id="image" name="image"  accept="image/*" />
		</form>
	</div>
	<script>
		//링크를 클릭했을때 
		document.querySelector("#profileLink").addEventListener("click", ()=>{
			// input type="file" 요소를 강제 클릭해서 파일 선택 창을 띄운다.
			document.querySelector("#image").click();
		});
		//새로운 이미지가 선택되었을때
		document.querySelector("#image").addEventListener("change", (e)=>{
			//선택된 파일 배열 객체를 얻어낸다
			const files=e.target.files;
			//만일 파일 데이터가 존재한다면
			if(files.length > 0){
				//파일로 부터 데이터를 읽어들일 객체 생성
				const reader=new FileReader();
				//파일 배열의 0 번방에 있는 파일을 data url 형식으로 읽어들인다 
				reader.readAsDataURL(files[0]);
				//다 읽었을때 실행할 함수 등록
				reader.onload=(event)=>{
					//읽은 문자열(data url 형식의 긴 문자열) 얻어내기
					const data=event.target.result;
					console.log(data);
					//img 요소를 만들어서 
					const img=document.createElement("img");
					//원하는 속성을 추가하고 
					img.setAttribute("src", data);
					img.setAttribute("id", "profileImage");
					//링크를 찾아서 
					const link=document.querySelector("#profileLink");
					//기존에 출력된 정보를 삭제하고 
					link.innerText="";
					//새로 img 요소를 추가 하기 
					link.append(img);
				};
			}
		});
		
		// div 의 참조값 
		const dropZone=document.querySelector("#dropZone");
		dropZone.addEventListener("dragover", (e)=>{
			e.preventDefault(); //기본 이벤트 막기
		});
		dropZone.addEventListener("drop", (e)=>{
			e.preventDefault(); //기본 이벤트 막기
			// event 객체 e 에는 drop 된 파일에 대한 정보가 들어 있다.
			const files=e.dataTransfer.files;
				
			//이미지 파일인지 여부를 알아내서 이미지 파일이 아니면 동작하지 않도록 설정
			const reg=/image/;
			if(!reg.test(files[0].type)){ //파일의 type 이 만일 정규표현식을 통과하지 못하면
				alert("이미지 파일이 아닙니다!");
				return; // 함수를 여기서 끝내기 
			}
			
			// input type="file" 에 drop 된 파일을 넣어주기
			document.querySelector("#image").files=files;
			
			if(files.length>0){
				//파일로 부터 데이터를 읽어들일 객체 생성
				const reader=new FileReader();
				//파일을 DataURL 형식의 문자열로 읽어들이기
				reader.readAsDataURL(files[0]);
				//로딩이 완료(파일데이터를 모드 읽었을때) 되었을때 실행할 함수 등록
				reader.onload=(event)=>{
					//읽은 파일 데이터 얻어내기 
					const data=event.target.result;
					// img 형식의 html 문자열을 만들어서 
					let img=`<img id="profileImage" src="\${data}">`;
					// id 가 profileLink 인 요소에 끼워넣는다(HTML 로 해석하도록 끼워넣기)
					document.querySelector("#profileLink").innerHTML=img;
				};
			}
		});
	</script>
</body>
</html>





