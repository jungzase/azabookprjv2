<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="panel">
	<h2 style="margin-top:0;">회원가입</h2>
	<form action="${pageContext.request.contextPath}/member/join" method="post" style="max-width:560px;">
		<div class="form-row">
			<label for="username">아이디</label>
			<input type="text" id="username" name="username" required>
		</div>
		<div class="form-row">
			<label for="password">비밀번호</label>
			<input type="password" id="password" name="password" required>
		</div>
		<div class="form-row">
			<label for="name">이름</label>
			<input type="text" id="name" name="name">
		</div>
		<div class="form-row">
			<label for="email">이메일</label>
			<input type="email" id="email" name="email">
		</div>
		<div class="form-row">
			<label for="hp">전화번호</label>
			<input type="text" id="hp" name="hp">
		</div>
		<div class="form-row">
			<label for="address">주소</label>
			<input type="text" id="address" name="address">
		</div>
		<input type="hidden" name="role" value="USER">
		<div class="inline-actions">
			<button type="submit" class="btn primary">회원가입</button>
			<a class="btn" href="${pageContext.request.contextPath}/member/login">로그인</a>
		</div>
	</form>
</div>
