<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="panel">
	<c:choose>
		<c:when test="${book != null}">
			<h2 style="margin-top:0;">도서 수정</h2>
		</c:when>
		<c:otherwise>
			<h2 style="margin-top:0;">도서 등록</h2>
		</c:otherwise>
	</c:choose>

	<form method="post" action="${pageContext.request.contextPath}/admin/books/save">
		<c:if test="${book != null}">
			<input type="hidden" name="isUpdate" value="true">
		</c:if>

		<div class="form-row">
			<label for="isbn">ISBN</label>
			<input id="isbn" type="text" name="isbn" value="${book.isbn}" ${book != null ? 'readonly="readonly"' : ''}>
		</div>
		<div class="form-row">
			<label for="productName">도서명</label>
			<input id="productName" type="text" name="productName" value="${book.productName}">
		</div>
		<div class="form-row">
			<label for="author">저자</label>
			<input id="author" type="text" name="author" value="${book.author}">
		</div>
		<div class="form-row">
			<label for="publisher">출판사</label>
			<input id="publisher" type="text" name="publisher" value="${book.publisher}">
		</div>
		<div class="form-row">
			<label for="publicationDate">출간일</label>
			<input id="publicationDate" type="date" name="publicationDate" value="${book.publicationDate}">
		</div>
		<div class="form-row">
			<label for="price">가격</label>
			<input id="price" type="number" name="price" value="${book.price}">
		</div>
		<div class="form-row">
			<label for="category">카테고리</label>
			<input id="category" type="text" name="category" value="${book.category}">
		</div>
		<div class="form-row">
			<label for="imgLink">이미지 주소</label>
			<input id="imgLink" type="text" name="imgLink" value="${book.imgLink}">
		</div>
		<div class="inline-actions">
			<button type="submit" class="btn primary">저장</button>
			<c:if test="${book != null}">
				<a class="btn" href="${pageContext.request.contextPath}/admin/books/delete?isbn=${book.isbn}" onclick="return confirm('도서를 삭제하시겠습니까?');">삭제</a>
			</c:if>
			<a class="btn" href="${pageContext.request.contextPath}/books/search">목록</a>
		</div>
	</form>
</div>
