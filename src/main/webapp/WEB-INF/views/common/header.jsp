<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
	<div class="container" style="display:flex; align-items:center; justify-content:space-between; gap:16px; padding:18px 0;">
		<div style="display:flex; align-items:center; gap:18px; flex-wrap:wrap;">
			<a href="${pageContext.request.contextPath}/" style="font-size:24px; font-weight:bold; color:#fff;">AZA Book</a>
			<a href="${pageContext.request.contextPath}/books/search" style="color:#d8edf8;">도서검색</a>
			<a href="${pageContext.request.contextPath}/cart/list" style="color:#d8edf8;">장바구니</a>
			<a href="${pageContext.request.contextPath}/order/history" style="color:#d8edf8;">주문내역</a>
		</div>
		<div style="display:flex; align-items:center; gap:12px; flex-wrap:wrap;">
			<form action="${pageContext.request.contextPath}/books/search" method="get" style="display:flex; gap:8px; flex-wrap:wrap;">
				<select name="category">
					<option value="">전체</option>
					<option value="소설">소설</option>
					<option value="에세이">에세이</option>
					<option value="IT">IT</option>
					<option value="역사">역사</option>
				</select>
				<input type="text" name="keyword" value="${param.keyword}" placeholder="제목 또는 저자 검색">
				<button type="submit" class="btn">검색</button>
			</form>
			<c:choose>
				<c:when test="${not empty sessionScope.loginMember}">
					<span style="color:#fff;">${sessionScope.loginMember.name}님</span>
					<a href="${pageContext.request.contextPath}/member/profile" style="color:#d8edf8;">마이페이지</a>
					<a href="${pageContext.request.contextPath}/member/logout" style="color:#d8edf8;">로그아웃</a>
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/member/join" style="color:#d8edf8;">회원가입</a>
					<a href="${pageContext.request.contextPath}/member/login" style="color:#d8edf8;">로그인</a>
				</c:otherwise>
			</c:choose>
			<c:if test="${sessionScope.isAdmin}">
				<a href="${pageContext.request.contextPath}/admin/books/form" style="color:#ffe4a8;">도서등록</a>
				<a href="${pageContext.request.contextPath}/admin/orders" style="color:#ffe4a8;">주문관리</a>
			</c:if>
		</div>
	</div>
</header>
