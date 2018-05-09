<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form action="ReplyReg" method="post">
<input type="hidden" name="id" value="${data.id }">

<table border="">
	<tr>
		<td>제목</td>
		<td><input type="text" name="title" value="${data.title }"></td>
	</tr>
	<tr>
		<td>글쓴이</td>
		<td><input type="text" name="pname"></td>
	</tr>
	<tr>
		<td>암호</td>
		<td><input type="password" name="pw"></td>
	</tr>
	<tr>
		<td>내용</td>
		<td><textarea name="content" rows="5" cols="30">${data.content }</textarea></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="댓글달기">
			<a href="List?id=${data.id }">목록</a>	
			<a href="Detail?id=${data.id }">뒤로</a>	
		</td>
	</tr>
</table>
</form>