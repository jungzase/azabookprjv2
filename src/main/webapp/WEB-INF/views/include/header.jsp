<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="cp" value="${pageContext.request.contextPath}" />

<div class="top-bar">
    <div class="top-bar-inner">
        <c:choose>
            <c:when test="${sessionScope.loginUser == null}">
                <a href="${cp}/member/login">로그인</a>
                <a href="${cp}/member/join">회원가입</a>
            </c:when>
            <c:otherwise>
                <span><strong>${sessionScope.loginUser.name}</strong>님 환영합니다</span>
                <a href="${cp}/member/mypage">마이페이지</a>
                <a href="${cp}/member/orders">주문내역</a>
                <a href="${cp}/member/logout">로그아웃</a>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<div class="header">
    <div class="header-inner">
        <div class="logo">
            <a href="${cp}/">AZABOOK</a>
        </div>

        <form class="search-box" action="${cp}/book/list" method="get">
            <input type="text" name="keyword" placeholder="도서명 저자 출판사로 검색해보세요">
            <button type="submit">검색</button>
        </form>

        <div class="header-icons">
            <a href="${cp}/book/list">도서목록</a>
            <a href="${cp}/cart/list">장바구니</a>
            <c:if test="${sessionScope.loginUser != null && sessionScope.loginUser.role == 'ADMIN'}">
                <a href="${cp}/admin/books">관리자</a>
            </c:if>
        </div>
    </div>
</div>

<div class="nav">
    <div class="nav-inner">
        <a href="${cp}/book/list">전체도서</a>
        <a href="${cp}/book/new">신상품</a>
        <a href="${cp}/book/list?categoryId=1">소설/시/희곡</a>
        <a href="${cp}/book/list?categoryId=2">자기계발</a>
        <a href="${cp}/book/list?categoryId=17">컴퓨터/모바일</a>
    </div>
</div>
