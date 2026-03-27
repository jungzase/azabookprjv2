<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AZABOOK</title>
<link rel="stylesheet" href="${cp}/resources/css/style.css">
</head>
<body>

<%@ include file="include/header.jsp" %>

<div class="main">
    <div class="hero">
        <div class="hero-text">
            <h1>당신이 찾는 모든 책,<br>AZABOOK에서 만나보세요</h1>
            <p>
                소설, 자기계발, 경제경영, 컴퓨터/모바일까지 다양한 분야의 도서를 쉽고 빠르게 검색하고<br>
                장바구니에 담아 편리하게 주문할 수 있는 온라인 서점입니다.
            </p>
            <a class="btn btn-green" href="${cp}/book/list">지금 쇼핑 시작하기</a>
        </div>
        <div style="font-size:120px;">📚</div>
    </div>

    <div class="panel">
        <div class="section-title">추천 도서</div>
        <div class="recommend-grid">
            <c:forEach var="book" items="${recommendedBooks}">
                <a href="${cp}/book/detail?isbn=${book.isbn}" class="recommend-card">
                    <div class="recommend-rank">TOP ${book.rankNum}</div>
                    <div class="recommend-thumb">
                        <c:choose>
                            <c:when test="${not empty book.imgLink}">
                                <img src="${book.imgLink}" alt="${book.bookName}" loading="lazy" referrerpolicy="no-referrer">
                            </c:when>
                            <c:otherwise>
                                <span>BOOK</span>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="recommend-title">${book.bookName}</div>
                    <div class="recommend-meta">${book.author}</div>
                </a>
            </c:forEach>
        </div>
    </div>
</div>

<%@ include file="include/footer.jsp" %>

</body>
</html>
