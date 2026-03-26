<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<link rel="stylesheet" href="${cp}/resources/css/style.css">
</head>
<body>

<%@ include file="../include/header.jsp" %>

<div class="main">
    <div class="section-title">장바구니</div>

    <c:choose>
        <c:when test="${empty cartList}">
            <div class="empty-box">장바구니에 담긴 상품이 없습니다.</div>
        </c:when>
        <c:otherwise>
            <div class="table-wrap">
                <table class="data-table">
                    <tr>
                        <th>도서명</th>
                        <th>가격</th>
                        <th>수량</th>
                        <th>합계</th>
                        <th>수량변경</th>
                        <th>삭제</th>
                    </tr>

                    <c:set var="totalPrice" value="0" />
                    <c:forEach var="c" items="${cartList}">
                        <c:set var="rowTotal" value="${c.price * c.quantity}" />
                        <c:set var="totalPrice" value="${totalPrice + rowTotal}" />

                        <tr>
                            <td class="left">${c.bookName}</td>
                            <td>${c.price}원</td>
                            <td>${c.quantity}</td>
                            <td>${rowTotal}원</td>
                            <td>
                                <form action="${cp}/cart/update" method="post" style="display:flex; justify-content:center; gap:8px;">
                                    <input type="hidden" name="cartId" value="${c.cartId}">
                                    <input type="number" name="quantity" value="${c.quantity}" min="1" class="input-small">
                                    <button type="submit" class="btn btn-primary">변경</button>
                                </form>
                            </td>
                            <td>
                                <form action="${cp}/cart/delete" method="post" style="margin:0;">
                                    <input type="hidden" name="cartId" value="${c.cartId}">
                                    <button type="submit" class="btn btn-red">삭제</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

            <div class="total-box">총 결제예정금액 : ${totalPrice}원</div>

            <div class="text-right mt-20">
                <a href="${cp}/book/list" class="btn btn-gray">쇼핑 계속하기</a>
                <a href="${cp}/order/checkout" class="btn btn-green">주문하기</a>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<%@ include file="../include/footer.jsp" %>

</body>
</html>