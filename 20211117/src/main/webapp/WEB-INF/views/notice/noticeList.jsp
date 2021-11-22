<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function noticeRead(str) {
		frm.no.value = str;
	//	alert(frm.no.value)
		frm.submit();
	}
</script>
</head>
<body>
	<jsp:include page="../home/header.jsp" />
	<div align="center">
		<div>
			<h1>공지사항 리스트</h1>
		</div>
		<div>
			<table border="1">
				<tr>
					<th width="70">No</th>
					<th width="100">작성자</th>
					<th width="300">제 목</th>
					<th width="100">작성일자</th>
					<th width="70">첨부파일</th>
				</tr>
				<c:forEach items="${notices }" var="notice">
					<tr>
					<tr onmouseover="this.style.background='#fcecae';"
						onmouseleave="this.style.background='#ffffff'"
						onclick="noticeRead('${notice.no }')">
						<td align="center">${notice.no }</td>
						<td align="center">${notice.name }</td>
						<td align="center">${notice.title }</td>
						<td align="center">${notice.wdate }</td>
						<td align="center"><c:if test="${not empty notice.fileName }">
								<img src="img/attachment_file.png" alt="첨부파일" width="20"
									height="20">
							</c:if></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<br>
		<div>
			<c:if test="${not empty id }">
				<button type="button" onclick="location.href='noticeForm.do'">글쓰기</button>
			</c:if>
		</div>
		<div>
			<form id="frm" action="noticeRead.do" method="post">
				<input type="hidden" id="no" name="no">
			</form>
		</div>
	</div>
</body>
</html>