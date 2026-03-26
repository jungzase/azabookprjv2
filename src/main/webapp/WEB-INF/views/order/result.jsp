<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문완료</title>
<link rel="stylesheet" href="${cp}/resources/css/style.css">
</head>
<body>

<%@ include file="../include/header.jsp" %>

<div class="main">
    <div class="panel text-center" style="padding:80px 30px;">
        <div style="font-size:70px; margin-bottom:20px;">🎉</div>
        <div class="section-title">주문이 완료되었습니다</div>
        <div style="font-size:18px; color:#4b5563; margin-bottom:20px;">
            주문번호 : <strong>#${orderId}</strong>
        </div>

        <a href="${cp}/member/orders" class="btn btn-primary">주문내역 보기</a>
        <a href="${cp}/book/list" class="btn btn-gray">쇼핑 계속하기</a>
    </div>
</div>

<%@ include file="../include/footer.jsp" %>

</body>
</html>