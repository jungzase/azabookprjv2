<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>재고 부족</title>
<link rel="stylesheet" href="${cp}/resources/css/style.css">
</head>
<body>

<%@ include file="../include/header.jsp" %>

<div class="main">
    <div class="panel stock-alert-panel">
        <div class="stock-alert-icon">!</div>
        <div class="section-title">재고가 부족합니다</div>
        <div class="stock-alert-text">
            <strong>${bookName}</strong>의 재고가 부족해 주문을 완료할 수 없습니다.
        </div>
        <div class="stock-alert-text">요청 수량: ${requestedQuantity}권</div>
        <div class="stock-alert-text">최대 가능 구매 수량: ${availableQuantity}권</div>

        <div class="stock-alert-actions">
            <a href="${cp}/cart/list" class="btn btn-primary">장바구니로 이동</a>
            <a href="#" onclick="history.back(); return false;" class="btn btn-gray">뒤로가기</a>
        </div>
    </div>
</div>

<%@ include file="../include/footer.jsp" %>

</body>
</html>
