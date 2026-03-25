<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="panel">
	<h2 style="margin-top:0;">마이페이지</h2>
	<c:choose>
		<c:when test="${empty member}">
			<div class="placeholder">로그인 정보가 없습니다.</div>
		</c:when>
		<c:otherwise>
			<form action="${pageContext.request.contextPath}/member/update" method="post" style="max-width:640px;">
				<div class="form-row">
					<label>회원번호</label>
					<input type="text" value="${member.user_id}" readonly="readonly">
				</div>
				<div class="form-row">
					<label for="username">아이디</label>
					<input type="text" id="username" name="username" value="${member.username}">
				</div>
				<div class="form-row">
					<label for="password">비밀번호</label>
					<input type="password" id="password" name="password" value="${member.password}">
				</div>
				<div class="form-row">
					<label for="name">이름</label>
					<input type="text" id="name" name="name" value="${member.name}">
				</div>
				<div class="form-row">
					<label for="email">이메일</label>
					<input type="email" id="email" name="email" value="${member.email}">
				</div>
				<div class="form-row">
					<label for="hp">전화번호</label>
					<input type="text" id="hp" name="hp" value="${member.hp}">
				</div>
				<div class="form-row">
					<label for="address">주소</label>
					<input type="text" id="address" name="address" value="${member.address}">
				</div>
				<div class="form-row">
					<label>권한</label>
					<input type="text" value="${member.role}" readonly="readonly">
				</div>
				<div class="form-row">
					<label>가입일</label>
					<input type="text" value="${member.reg_date}" readonly="readonly">
				</div>
				<div class="inline-actions">
					<button type="submit" class="btn primary">회원정보 수정</button>
					<a class="btn" href="${pageContext.request.contextPath}/member/orders">주문내역 조회</a>
				</div>
			</form>
		</c:otherwise>
	</c:choose>
</div>
