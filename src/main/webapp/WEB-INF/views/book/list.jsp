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
        <a href="${cp}/book/list?categoryId=1" class="${categoryId == 1 ? 'active' : ''}">소설/시/희곡</a>
        <a href="${cp}/book/list?categoryId=2" class="${categoryId == 2 ? 'active' : ''}">자기계발</a>
        <a href="${cp}/book/list?categoryId=3" class="${categoryId == 3 ? 'active' : ''}">경제경영</a>
        <a href="${cp}/book/list?categoryId=4" class="${categoryId == 4 ? 'active' : ''}">외국어</a>
        <a href="${cp}/book/list?categoryId=5" class="${categoryId == 5 ? 'active' : ''}">인문학</a>
        <a href="${cp}/book/list?categoryId=6" class="${categoryId == 6 ? 'active' : ''}">만화/라이트노벨</a>
        <a href="${cp}/book/list?categoryId=7" class="${categoryId == 7 ? 'active' : ''}">예술/대중문화</a>
        <a href="${cp}/book/list?categoryId=8" class="${categoryId == 8 ? 'active' : ''}">과학</a>
        <a href="${cp}/book/list?categoryId=9" class="${categoryId == 9 ? 'active' : ''}">수험서/자격증</a>
        <a href="${cp}/book/list?categoryId=10" class="${categoryId == 10 ? 'active' : ''}">에세이</a>
        <a href="${cp}/book/list?categoryId=11" class="${categoryId == 11 ? 'active' : ''}">사회과학</a>
        <a href="${cp}/book/list?categoryId=12" class="${categoryId == 12 ? 'active' : ''}">어린이</a>
        <a href="${cp}/book/list?categoryId=13" class="${categoryId == 13 ? 'active' : ''}">건강/취미</a>
        <a href="${cp}/book/list?categoryId=14" class="${categoryId == 14 ? 'active' : ''}">유아</a>
        <a href="${cp}/book/list?categoryId=15" class="${categoryId == 15 ? 'active' : ''}">역사</a>
        <a href="${cp}/book/list?categoryId=16" class="${categoryId == 16 ? 'active' : ''}">종교/역학</a>
        <a href="${cp}/book/list?categoryId=17" class="${categoryId == 17 ? 'active' : ''}">컴퓨터/모바일</a>
    </div>

    <c:choose>
        <c:when test="${empty bookList}">
            <div class="empty-box">검색된 도서가 없습니다.</div>
        </c:when>
        <c:otherwise>
            <div class="book-grid">
                <c:forEach var="b" items="${bookList}">
                    <div class="book-card">
                        <a href="${cp}/book/detail?isbn=${b.isbn}" class="book-link-area">
                            <div class="book-thumb">
                                <c:choose>
                                    <c:when test="${not empty b.imgLink}">
                                        <img src="${b.imgLink}" alt="${b.bookName}" loading="lazy" referrerpolicy="no-referrer">
                                    </c:when>
                                    <c:otherwise>
                                        <span>BOOK</span>
                                    </c:otherwise>
                                </c:choose>
                                <div class="book-hover-overlay">상세보기</div>
                            </div>
                            <div class="book-info">
                                <div class="book-category">${b.categoryName}</div>
                                <div class="book-name">${b.bookName}</div>
                                <div class="book-author">${b.author} | ${b.publisher}</div>
                                <div class="book-price">${b.price}원</div>
                            </div>
                        </a>

                        <div class="book-actions">
                            <a href="${cp}/cart/add?isbn=${b.isbn}&quantity=1&redirectURL=/book/list" class="btn btn-green">장바구니</a>
                            <a href="${cp}/order/direct?isbn=${b.isbn}&quantity=1" class="btn btn-primary">바로구매</a>
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
