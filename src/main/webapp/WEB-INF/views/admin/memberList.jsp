<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 - 회원관리</title>
<link rel="stylesheet" href="${cp}/resources/css/style.css">
</head>
<body>

<%@ include file="../include/header.jsp" %>

<div class="main">
    <div class="flex-between mb-20">
        <div class="section-title" style="margin-bottom:0;">관리자 - 회원 관리</div>
        <div>
            <a href="${cp}/admin/books" class="btn btn-gray">도서 관리</a>
            <a href="${cp}/admin/orders" class="btn btn-primary">주문 관리</a>
        </div>
    </div>

    <div class="table-wrap">
        <table class="data-table">
            <tr>
                <th>회원번호</th>
                <th>아이디</th>
                <th>이름</th>
                <th>이메일</th>
                <th>전화번호</th>
                <th>주소</th>
                <th>권한</th>
            </tr>

            <c:forEach var="m" items="${memberList}">
                <tr>
                    <td>${m.userId}</td>
                    <td>${m.username}</td>
                    <td>${m.name}</td>
                    <td>${m.email}</td>
                    <td>${m.hp}</td>
                    <td class="left">${m.address}</td>
                    <td>${m.role}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<%@ include file="../include/footer.jsp" %>

</body>
</html>