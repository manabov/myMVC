<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form action="InsertReg" method="post" enctype="multipart/form-data">

<table border="">
	<tr>
		<td>제목</td>
		<td><input type="text" name="title"></td>
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
		<td>파일</td>
		<td><input type="file" name="upfile"></td>
	</tr>
	<tr>
		<td>내용</td>
		<td><textarea cols="30" rows="5" name="content"></textarea></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="글 등록">
			<a href="List?page=${param.page }">뒤로</a>
			
		</td>
	</tr>
</table>

</form>