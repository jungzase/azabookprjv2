<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="panel">
	<h2 style="margin-top:0;">회원 정보 수정 (Admin)</h2>
	<form action="${pageContext.request.contextPath}/admin/members/update" method="post" style="max-width:600px;">
		<input type="hidden" name="user_id" value="${m.user_id}">
		
		<div class="form-row">
			<label>아이디 (수정불가)</label>
			<input type="text" value="${m.username}" readonly style="background:#f0f0f0;">
		</div>
		<div class="form-row">
			<label for="name">이름</label>
			<input type="text" id="name" name="name" value="${m.name}">
		</div>
		<div class="form-row">
			<label for="email">이메일</label>
			<input type="email" id="email" name="email" value="${m.email}">
		</div>
		<div class="form-row">
			<label for="hp">전화번호</label>
			<input type="text" id="hp" name="hp" value="${m.hp}">
		</div>
		<div class="form-row">
			<label for="address">주소</label>
			<input type="text" id="address" name="address" value="${m.address}">
		</div>
		<div class="form-row">
			<label for="role">권한 설정</label>
			<select name="role" id="role" style="padding:8px; border-radius:4px;">
				<option value="USER" ${m.role == 'USER' ? 'selected' : ''}>일반 사용자(USER)</option>
				<option value="ADMIN" ${m.role == 'ADMIN' ? 'selected' : ''}>관리자(ADMIN)</option>
			</select>
		</div>

		<div class="inline-actions" style="margin-top:20px;">
			<button type="submit" class="btn primary">수정 내용 저장</button>
			<a href="${pageContext.request.contextPath}/admin/members" class="btn">취소</a>
		</div>
	</form>
</div>