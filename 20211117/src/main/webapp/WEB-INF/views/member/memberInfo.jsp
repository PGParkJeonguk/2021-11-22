<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
/*
   function CallProcess(n) {
      if(n == 1){
         frm.action = "memberUpdate.do";
      } else {
         var str = confirm("회원탈퇴를 하시겠습니까?");
         if(str) {
            frm.action = "memberDelete.do";            
         } else {
            return false;
         }
      }
      
      frm.submit();
   }
 */
 	$(function(){
 		$("#btn1").click(function(){
 			frm.action = "memberUpdate.do";    
 			frm.submit();
 		});
 		$("#btn2").click(function(){
 			var str = confirm("회원탈퇴를 하시겠습니까?");
 	         if(str) 
 	            frm.action = "memberDelete.do";            
 	          else 
 	            return false;
 	        frm.submit();
 		});
 	})
</script>
</head>
<body>
<jsp:include page="../home/header.jsp"/>
<div align = "center">
   <div><h1>나의 정보 보기</h1></div>
   <div>
      <table border="1">
         <tr>
            <th width="150">ID</th>
            <td width="150">${member.id }</td>
            <th width="150">이름</th>
            <td width="150">${member.name }</td>
         </tr>
         <tr>
            <th>PASSWORD</th>
            <td>****</td>
            <th>권한</th>
            <td>${member.author }</td>
         </tr>
         <tr>
            <th>전화번호</th>
            <td colspan="3">${member.tel }</td>
         </tr>
         <tr>
            <th>주   소</th>
            <td colspan="3">${member.address }</td>
         </tr>
      </table>
   </div><br>
   <div>
      <button type="button" id="btn1" onclick="CallProcess(1)">수정</button>&nbsp;&nbsp;&nbsp;
      <button type="button" id="btn2" onclick="CallProcess(2)">탈퇴</button>
   </div>
   <div>
      <form id="frm" method="post"> <!-- get이 아닌 post 로 처리하기 위해 -->
         <input type="hidden" id="id" name="id" value="${member.id }">
      </form> 
   </div>
</div>
</body>
</html>