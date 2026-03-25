<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서목록</title>
<link rel="stylesheet" href="${cp}/resources/css/style.css">
</head>
<body>

<%@ include file="../include/header.jsp" %>

<div class="main">
    <div class="flex-between mb-20">
        <div class="section-title" style="margin-bottom:0;">도서 목록</div>
    </div>

    <div class="category-bar">
        <a href="${cp}/book/list" class="${empty categoryId ? 'active' : ''}">전체</a>
        <a href="${cp}/book/list?categoryId=1" class="${categoryId == 1 ? 'active' : ''}">소설</a>
        <a href="${cp}/book/list?categoryId=2" class="${categoryId == 2 ? 'active' : ''}">IT</a>
        <a href="${cp}/book/list?categoryId=3" class="${categoryId == 3 ? 'active' : ''}">자기계발</a>
    </div>

    <c:choose>
        <c:when test="${empty bookList}">
            <div class="empty-box">검색된 도서가 없습니다.</div>
        </c:when>
        <c:otherwise>
            <div class="book-grid">
                <c:forEach var="b" items="${bookList}">
                    <div class="book-card">
                        <div class="book-thumb">BOOK</div>
                        <div class="book-info">
                            <div class="book-category">${b.categoryName}</div>
                            <div class="book-name">${b.bookName}</div>
                            <div class="book-author">${b.author} | ${b.publisher}</div>
                            <div class="book-price">${b.price}원</div>

                            <div class="book-actions">
                                <a href="${cp}/book/detail?isbn=${b.isbn}" class="btn btn-primary">상세보기</a>

                              <c:if test="${sessionScope.loginUser != null}">
								    <form action="${cp}/cart/add" method="post" style="margin:0;">
								        <input type="hidden" name="isbn" value="${b.isbn}">
								        <input type="hidden" name="quantity" value="1">
								        <button type="submit" class="btn btn-green">장바구니</button>
								    </form>
							 </c:if>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<%@ include file="../include/footer.jsp" %>

</body>
</html>