<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ct" tagdir="/WEB-INF/tags" %>

<table border = "">
	<tr>
		<td>제목</td>
		<td>${data.title }</td>
	</tr>
	<tr>
		<td>글쓴이</td>
		<td>${data.pname}</td>
	</tr>
	<tr>
		<td>등록일</td>
		<td><fmt:formatDate value="${data.reg_date }" pattern="yyyy-MM-dd(E) HH:mm"/></td>
	</tr>
	<tr>
		<td>조회수</td>
		<td>${data.cnt}</td>
	</tr>
<c:if test="${data.upfile != '' }">	
	<tr>
		<td>파일</td>
		<td>
			<c:choose>
				<c:when test="${data.imgChk }">
					<img src="../up/${data.upfile }">
				</c:when>
				<c:otherwise>
					<a href="FileDown?file=${data.upfile }">${data.upfile }</a>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	
</c:if>	
	<tr>
		<td>내용</td>
		<td><ct:conBr>${data.content}</ct:conBr></td>
	</tr>
	<tr>
		<td colspan="2">
			<a href="List?id=${data.id }">목록으로</a>
			<a href="DeleteForm?id=${data.id }">삭제</a>
			<a href="ModifyForm?id=${data.id }">수정</a>
			<a href="ReplyForm?id=${data.id }">답변</a>
		</td>
	</tr>

</table>