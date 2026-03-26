<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 - 도서관리</title>
<link rel="stylesheet" href="${cp}/resources/css/style.css">
</head>
<body>

<%@ include file="../include/header.jsp" %>

<div class="main">
    <div class="flex-between mb-20">
        <div class="section-title" style="margin-bottom:0;">관리자 - 도서 관리</div>
        <div>
            <a href="${cp}/admin/book/form" class="btn btn-green">도서 등록</a>
            <a href="${cp}/admin/members" class="btn btn-gray">회원 관리</a>
            <a href="${cp}/admin/orders" class="btn btn-primary">주문 관리</a>
        </div>
    </div>

    <div class="table-wrap">
        <table class="data-table">
            <tr>
                <th>ISBN</th>
                <th>도서명</th>
                <th>카테고리</th>
                <th>가격</th>
                <th>재고</th>
                <th>수정</th>
                <th>삭제</th>
            </tr>

            <c:forEach var="b" items="${bookList}">
                <tr>
                    <td>${b.isbn}</td>
                    <td class="left">${b.bookName}</td>
                    <td>${b.categoryName}</td>
                    <td>${b.price}</td>
                    <td>${b.stock}</td>
                    <td>
                        <a href="${cp}/admin/book/form?isbn=${b.isbn}" class="btn btn-primary">수정</a>
                    </td>
                    <td>
                        <form action="${cp}/admin/book/delete" method="post" style="margin:0;">
                            <input type="hidden" name="isbn" value="${b.isbn}">
                            <button type="submit" class="btn btn-red">삭제</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<%@ include file="../include/footer.jsp" %>

</body>
</html>