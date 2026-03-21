<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
	<c:when test="${book == null}">
		<div class="placeholder">선택한 도서 정보를 찾을 수 없다.</div>
	</c:when>
	<c:otherwise>
		<div class="panel" style="display:grid; grid-template-columns:minmax(220px, 280px) 1fr; gap:24px;">
			<div>
				<c:choose>
					<c:when test="${empty book.imgLink}">
						<div class="book-thumb"></div>
					</c:when>
					<c:otherwise>
						<img class="book-thumb" src="${book.imgLink}" alt="${book.productName}">
					</c:otherwise>
				</c:choose>
			</div>
			<div>
				<h2 style="margin-top:0;">${book.productName}</h2>
				<div class="book-meta">저자 ${book.author}</div>
				<div class="book-meta">출판사 ${book.publisher}</div>
				<div class="book-meta">출간일 ${book.publicationDate}</div>
				<div class="book-meta">가격 ${book.price}원</div>
				<div class="book-meta">카테고리 ${book.category}</div>
				<div class="book-meta">ISBN ${book.isbn}</div>
				<div class="inline-actions">
					<a class="btn primary" href="${pageContext.request.contextPath}/cart/list">장바구니 담기</a>
					<a class="btn" href="${pageContext.request.contextPath}/order/checkout">구매하기</a>
					<a class="btn" href="${pageContext.request.contextPath}/books/search">목록으로</a>
					<c:if test="${sessionScope.isAdmin}">
						<a class="btn" href="${pageContext.request.contextPath}/admin/books/form?isbn=${book.isbn}">수정</a>
						<a class="btn" href="${pageContext.request.contextPath}/admin/books/delete?isbn=${book.isbn}" onclick="return confirm('도서를 삭제하시겠습니까?');">삭제</a>
					</c:if>
				</div>
			</div>
		</div>
	</c:otherwise>
</c:choose>
