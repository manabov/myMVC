<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<form action="DeleteReg">
<input type="hidden" value="${param.id }" name="id">
<table border="">
	<tr>
		<td>암호인증</td>
		<td><input type="password" name="pw"></td>
	</tr>
	<tr>
		<td colspan="2" align="right">
			<input type="submit" value="삭제">
			<a href="List?id=${param.id }">목록</a>
			<a href="Detail?id=${param.id }">뒤로</a>
		</td>
	</tr>
</table>
</form>    