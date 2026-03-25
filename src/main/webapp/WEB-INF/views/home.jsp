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
                IT, 소설, 자기계발까지 다양한 도서를 쉽고 빠르게 검색하고<br>
                장바구니에 담아 편리하게 주문할 수 있는 온라인 도서 쇼핑몰입니다.
            </p>
            <a class="btn btn-primary" href="${cp}/book/list">도서 보러가기</a>
        </div>
        <div style="font-size:120px;">📚</div>
    </div>

    <div class="panel">
        <div class="section-title">AZABOOK 주요 기능</div>
        <div style="line-height:2; font-size:16px; color:#374151;">
            <div>✔ 회원가입, 로그인, 마이페이지, 주문내역 조회</div>
            <div>✔ 도서 목록 조회, 카테고리별 검색, 키워드 검색</div>
            <div>✔ 장바구니 추가, 수량 변경, 삭제, 주문 진행</div>
            <div>✔ 주문 생성, 배송정보 입력, 주문 상태 관리</div>
            <div>✔ 관리자 도서 관리, 회원 관리, 주문 관리</div>
        </div>
    </div>

    <div class="mt-30 text-center">
        <a class="btn btn-green" href="${cp}/book/list">지금 쇼핑 시작하기</a>
    </div>
</div>

<%@ include file="include/footer.jsp" %>

</body>
</html>