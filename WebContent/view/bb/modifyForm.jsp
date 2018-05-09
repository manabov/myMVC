<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    
<script>

<c:if test="${msg != null}">
	alert("${msg}");
</c:if>


function fileDelete() {
	
	if(confirm("수정불가한데도??")) {
		var frm = document.frm;
		frm.action="FileDelete";
		frm.submit();
		
	}
	
}

</script>    
    
    
<form name="frm" action="ModifyReg" method="post" enctype="multipart/form-data">
<input type="hidden" name="id" value="${data.id }">
<input type="hidden" name="seq" value="${data.seq}">

<table border="">
	<tr>
		<td>제목</td>
		<td><input type="text" name="title" value="${data.title }"></td>
	</tr>
	<tr>
		<td>글쓴이</td>
		<td><input type="text" name="pname" value="${data.pname}"></td>
	</tr>
	<tr>
		<td>암호</td>
		<td><input type="password" name="pw"></td>
	</tr>

<c:choose>
<c:when test="${data.seq ==0 }">	
	<tr>
		<td>파일</td>
		<td>
			<c:choose>
				<c:when test="${data.upfile != '' }"> ${data.upfile }
					<input type="button" value="파일삭제" onclick="fileDelete()">
					<input type="hidden" name="upfile" value="${data.upfile }">
				</c:when>
				
				<c:otherwise>
					<input type="file" name="upfile">
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</c:when>
<c:otherwise>
	<input type="hidden" name="upfile" value="">
</c:otherwise>
</c:choose>

	<tr>
		<td>내용</td>
		<td><textarea name="content" cols="30" rows="5">${data.content }</textarea></td>
	</tr>
	
	<tr>
		<td colspan="2" align="right">
			<input type="submit" value="수정하기">
			<a href="ModifyForm?id=${data.id }">초기화</a>
			<a href="List?id=${data.id }">목록</a>
			<a href="Detail?id=${data.id }">뒤로</a>
		</td>
	</tr>
</table>
</form>
   