<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AZA Book</title>
<style>
body { margin: 0; font-family: Arial, sans-serif; background: #f6f6f0; color: #222; }
a { color: #1155aa; text-decoration: none; }
a:hover { text-decoration: underline; }
header, footer { background: #1f2a30; color: #f4f4f4; }
main { min-height: calc(100vh - 140px); padding: 24px 0; }
.container { width: min(1080px, calc(100% - 32px)); margin: 0 auto; }
.panel { background: #fff; border: 1px solid #d8d8d8; border-radius: 14px; padding: 20px; }
.grid3 { display: grid; grid-template-columns: repeat(auto-fit, minmax(220px, 1fr)); gap: 16px; }
.book-card { border: 1px solid #ddd; border-radius: 12px; padding: 14px; background: #fff; }
.book-thumb { width: 100%; aspect-ratio: 3 / 4; object-fit: cover; border-radius: 10px; background: #e9ecef; }
.book-meta { font-size: 14px; color: #555; margin-top: 6px; }
.list-item { display: grid; grid-template-columns: 120px 1fr; gap: 18px; border: 1px solid #ddd; border-radius: 12px; padding: 16px; margin-top: 16px; background: #fff; }
.btn { display: inline-block; padding: 10px 14px; border: 1px solid #b9c2c9; border-radius: 10px; background: #fff; color: #222; }
.btn.primary { background: #1f2a30; border-color: #1f2a30; color: #fff; }
.form-row { margin-bottom: 14px; }
.form-row label { display: block; margin-bottom: 6px; font-weight: bold; }
.form-row input, .form-row select { width: 100%; box-sizing: border-box; padding: 10px 12px; border: 1px solid #c7c7c7; border-radius: 8px; }
.inline-actions { display: flex; gap: 10px; flex-wrap: wrap; margin-top: 18px; }
.placeholder { padding: 18px; border: 1px dashed #c7c7c7; border-radius: 12px; background: #fbfbfb; }
.alert { margin-bottom: 18px; padding: 14px 16px; border-radius: 12px; background: #fff6d8; border: 1px solid #f0d98a; }
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<main>
		<div class="container">
			<c:if test="${not empty message}">
				<div class="alert">${message}</div>
			</c:if>
			<jsp:include page="/WEB-INF/views/${mainpage}.jsp" />
		</div>
	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>
