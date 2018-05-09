<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<table border="">

	<tr>
		<th>번호</th><th>제목</th><th>글쓴이</th><th>등록일</th><th>파일명</th><th>조회수</th>
	</tr>
	
<c:choose>
<c:when test="${data.size() == 0 }">
	<tr><td>글이 없습니다.</td></tr>
</c:when>
<c:otherwise>
	
	<c:forEach var="i" items="${data }" varStatus="no">
		<tr>
			<td>${no.index + start }</td>
			<td>
			<c:if test="${i.lev > 0 }">
				<c:forEach begin="1" end="${i.lev }">
					&nbsp;
				</c:forEach>
				└
			</c:if>
			
			<a href="Detail?id=${i.id}">${i.title}</a></td>
			
			<td>${i.pname }</td>
			<td><fmt:formatDate value="${i.reg_date }" pattern="yyyy-MM-dd(EE)"/></td>
			<td>${i.upfile}</td>
			<td>${i.cnt}</td>
			
		</tr>
	</c:forEach>

	<tr>
		<td colspan="6" align="center">
			<c:if test="${startPage > 1 }">
				<a href="List?page=1">[처음]</a>
				<a href="List?page=${startPage -1 }">◀</a>
			</c:if>
			
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<c:choose>
					<c:when test="${i == page }">
						[${i }]
					</c:when>
					<c:otherwise>
						<a href="List?page=${i }">${i }</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${totalPage > endPage }">
				<a href="List?page=${endPage + 1 }">▶</a>
				<a href="List?page=${totalPage }">[마지막]</a>
			</c:if>
		</td>
	</tr>
</c:otherwise>
</c:choose>	
	<tr>
		<td colspan="6" align="right">
			<a href="InsertForm?page=${page }">글쓰기</a>
		</td>
		
	</tr>

</table>    