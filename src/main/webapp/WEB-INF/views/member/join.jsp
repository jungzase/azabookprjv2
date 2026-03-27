<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="${cp}/resources/css/style.css">
</head>
<body>

<%@ include file="../include/header.jsp" %>

<div class="main">
    <div class="join-box">
        <div class="join-title">회원가입</div>

        <form action="${cp}/member/join" method="post">
            <div class="form-group">
                <label>아이디</label>
                <input type="text" name="username" class="form-control" placeholder="아이디">
            </div>

            <div class="form-group">
                <label>비밀번호</label>
                <input type="password" name="password" class="form-control" placeholder="비밀번호">
            </div>

            <div class="form-group">
                <label>이름</label>
                <input type="text" name="name" class="form-control" placeholder="이름">
            </div>

            <div class="form-group">
                <label>이메일</label>
                <input type="text" name="email" class="form-control" placeholder="이메일">
            </div>

            <div class="form-group">
                <label>전화번호</label>
                <input type="text" name="hp" class="form-control" placeholder="010-0000-0000">
            </div>

            <div class="form-group">
                <label>주소</label>
                <input type="text" name="address" class="form-control" placeholder="주소">
            </div>

            <button type="submit" class="btn btn-primary" style="width:100%;">회원가입</button>
        </form>
    </div>
</div>

<%@ include file="../include/footer.jsp" %>

</body>
</html>