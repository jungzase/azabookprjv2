<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 상세</title>
<link rel="stylesheet" href="${cp}/resources/css/style.css">
</head>
<body>

<%@ include file="../include/header.jsp" %>

<div class="main">
    <div class="detail-box">
        <div class="detail-thumb">
            <c:choose>
                <c:when test="${not empty book.imgLink}">
                    <img src="${book.imgLink}" alt="${book.bookName}" referrerpolicy="no-referrer">
                </c:when>
                <c:otherwise>
                    <span>BOOK</span>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="detail-info">
            <div class="detail-title">${book.bookName}</div>

            <div class="detail-meta">
                <div><strong>ISBN</strong> : ${book.isbn}</div>
                <div><strong>저자</strong> : ${book.author}</div>
                <div><strong>출판사</strong> : ${book.publisher}</div>
                <div><strong>출간일</strong> : ${book.publicationDate}</div>
                <div><strong>카테고리</strong> : ${book.categoryName}</div>
                <div><strong>재고</strong> : ${book.stock}</div>
            </div>

            <div class="detail-price">${book.price}원</div>

            <div class="detail-desc">${book.description}</div>

            <div style="display:flex; gap:10px; align-items:center;">
                <a href="${cp}/book/list" class="btn btn-gray">목록으로</a>

                <c:if test="${sessionScope.loginUser != null}">
                    <form action="${cp}/cart/add" method="post" style="display:flex; gap:8px; align-items:center; margin:0;">
                        <input type="hidden" name="isbn" value="${book.isbn}">
                        <input type="number" name="quantity" value="1" min="1" class="input-small">
                        <button type="submit" class="btn btn-green">장바구니 담기</button>
                    </form>
                </c:if>
            </div>
        </div>
    </div>
</div>

<%@ include file="../include/footer.jsp" %>

</body>
</html>
