<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<link rel="stylesheet" href="${cp}/resources/css/style.css">
</head>
<body>

<%@ include file="../include/header.jsp" %>

<div class="main">
    <div class="section-title">마이페이지</div>

    <div class="panel">
        <form action="${cp}/member/update" method="post">
            <input type="hidden" name="userId" value="${member.userId}">

            <table class="form-table">
                <tr>
                    <th>아이디</th>
                    <td><input type="text" name="username" value="${member.username}" class="form-control" readonly></td>
                </tr>
                <tr>
                    <th>비밀번호</th>
                    <td><input type="password" name="password" value="${member.password}" class="form-control"></td>
                </tr>
                <tr>
                    <th>이름</th>
                    <td><input type="text" name="name" value="${member.name}" class="form-control"></td>
                </tr>
                <tr>
                    <th>이메일</th>
                    <td><input type="text" name="email" value="${member.email}" class="form-control"></td>
                </tr>
                <tr>
                    <th>전화번호</th>
                    <td><input type="text" name="hp" value="${member.hp}" class="form-control"></td>
                </tr>
                <tr>
                    <th>주소</th>
                    <td><input type="text" name="address" value="${member.address}" class="form-control"></td>
                </tr>
                <tr>
                    <th>권한</th>
                    <td><input type="text" value="${member.role}" class="form-control" readonly></td>
                </tr>
            </table>

            <div class="text-right mt-20">
                <button type="submit" class="btn btn-primary">회원정보 수정</button>
                <a href="${cp}/member/orders" class="btn btn-gray">주문내역</a>
            </div>
        </form>
    </div>
</div>

<%@ include file="../include/footer.jsp" %>

</body>
</html>