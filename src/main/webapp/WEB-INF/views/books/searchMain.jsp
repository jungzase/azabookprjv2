<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="panel">
	<h2 style="margin-top:0;">도서 목록 조회</h2>
	<p>카테고리와 키워드를 조합해서 도서를 검색할 수 있다.</p>
</div>

<c:choose>
	<c:when test="${books == null}">
		<div class="placeholder" style="margin-top:18px;">검색 조건을 입력하면 결과가 표시된다. 키워드 없이 카테고리만 선택해도 조회된다.</div>
	</c:when>
	<c:when test="${empty books}">
		<div class="placeholder" style="margin-top:18px;">검색 결과가 없다.</div>
	</c:when>
	<c:otherwise>
		<c:forEach var="book" items="${books}">
			<div class="list-item">
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
					<div style="font-size:22px; font-weight:bold;">
						<a href="${pageContext.request.contextPath}/books/detail?isbn=${book.isbn}">${book.productName}</a>
					</div>
					<div class="book-meta">저자 ${book.author}</div>
					<div class="book-meta">출판사 ${book.publisher}</div>
					<div class="book-meta">출간일 ${book.publicationDate}</div>
					<div class="book-meta">가격 ${book.price}원</div>
					<div class="book-meta">카테고리 ${book.category}</div>
					<div class="inline-actions">
						<a class="btn" href="${pageContext.request.contextPath}/books/detail?isbn=${book.isbn}">상세보기</a>
						<a class="btn" href="${pageContext.request.contextPath}/cart/list">장바구니</a>
						<c:if test="${sessionScope.isAdmin}">
							<a class="btn" href="${pageContext.request.contextPath}/admin/books/form?isbn=${book.isbn}">수정</a>
							<a class="btn" href="${pageContext.request.contextPath}/admin/books/delete?isbn=${book.isbn}" onclick="return confirm('도서를 삭제하시겠습니까?');">삭제</a>
						</c:if>
					</div>
				</div>
			</div>
		</c:forEach>
	</c:otherwise>
</c:choose>
