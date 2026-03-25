<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="panel">
	<div class="login-wrap">
		<h2 style="margin-top:0;">로그인</h2>

		<form action="${pageContext.request.contextPath}/member/login" method="post" class="login-form">
			<div class="form-row">
				<label for="username">아이디</label>
				<input type="text" id="username" name="username" placeholder="아이디를 입력하세요" required>
			</div>

			<div class="form-row">
				<label for="password">비밀번호</label>
				<input type="password" id="password" name="password" placeholder="비밀번호를 입력하세요" required>
			</div>

			<div class="form-actions">
				<button type="submit" class="btn-login">로그인</button>
			</div>
		</form>

		<div class="login-links">
			<a href="${pageContext.request.contextPath}/member/join">회원가입</a>
			|
			<a href="${pageContext.request.contextPath}/member/findid">아이디찾기</a>
			|
			<a href="${pageContext.request.contextPath}/member/findpassword">비밀번호찾기</a>
		</div>
	</div>
</div>

<style>
.login-form { max-width: 400px; }
.login-wrap { width: 350px; margin: 0 auto; }
.form-row { display: flex; flex-direction: column; margin-bottom: 15px; }
.form-row label { margin-bottom: 6px; font-weight: bold; }
.form-row input { padding: 10px 12px; border: 1px solid #ccc; border-radius: 8px; font-size: 14px; }
.form-actions { margin-top: 20px; }
.btn-login { width: 100%; padding: 12px; border: none; border-radius: 8px; background-color: #222; color: white; font-size: 15px; cursor: pointer; }
.btn-login:hover { opacity: 0.9; }
.login-links { margin-top: 15px; font-size: 14px; text-align: center; }
.login-links a { margin: 0 8px; text-decoration: none; color: #0066cc; }
.login-links a:hover { text-decoration: underline; }
</style>
