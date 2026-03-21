<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="panel">
	<h2 style="margin-top:0;">추천 도서</h2>
	<p>도서 판매와 관리 기능을 한 화면 구조에서 시작할 수 있도록 기본 홈을 구성했다.</p>
</div>

<div class="grid3" style="margin-top:18px;">
	<c:choose>
		<c:when test="${empty recommend}">
			<div class="placeholder">추천 도서 데이터가 없으면 여기에 빈 상태가 표시된다.</div>
		</c:when>
		<c:otherwise>
			<c:forEach var="book" items="${recommend}">
				<div class="book-card">
					<a href="${pageContext.request.contextPath}/books/detail?isbn=${book.isbn}">
						<c:choose>
							<c:when test="${empty book.imgLink}">
								<div class="book-thumb"></div>
							</c:when>
							<c:otherwise>
								<img class="book-thumb" src="${book.imgLink}" alt="${book.productName}">
							</c:otherwise>
						</c:choose>
					</a>
					<div style="margin-top:12px; font-weight:bold;">${book.productName}</div>
					<div class="book-meta">저자 ${book.author}</div>
					<div class="book-meta">카테고리 ${book.category}</div>
				</div>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</div>
