<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="${cp}/resources/css/style.css">
</head>
<body>

<%@ include file="../include/header.jsp" %>

<div class="main">
    <div class="login-box">
        <div class="login-title">로그인</div>

        <c:if test="${not empty msg}">
            <div class="notice-box">${msg}</div>
        </c:if>

        <form action="${cp}/member/login" method="post">
            <input type="hidden" name="redirectURL" value="${redirectURL}">

            <div class="form-group">
                <label>아이디</label>
                <input type="text" name="username" class="form-control" placeholder="아이디를 입력하세요">
            </div>

            <div class="form-group">
                <label>비밀번호</label>
                <input type="password" name="password" class="form-control" placeholder="비밀번호를 입력하세요">
            </div>

            <button type="submit" class="btn btn-primary" style="width:100%;">로그인</button>
        </form>

        <div class="text-center mt-20">
            <a href="${cp}/member/join">회원가입 하러가기</a>
        </div>
    </div>
</div>

<%@ include file="../include/footer.jsp" %>

</body>
</html>
