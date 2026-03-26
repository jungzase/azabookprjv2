<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문하기</title>
<link rel="stylesheet" href="${cp}/resources/css/style.css">
</head>
<body>

<%@ include file="../include/header.jsp" %>

<div class="main">
    <div class="section-title">주문 / 결제</div>

    <div class="panel mb-20">
        <div class="sub-title">주문 상품</div>

        <div class="table-wrap">
            <table class="data-table">
                <tr>
                    <th>도서명</th>
                    <th>가격</th>
                    <th>수량</th>
                    <th>합계</th>
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
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div class="total-box">총 결제금액 : ${totalPrice}원</div>
    </div>

    <div class="panel">
        <div class="sub-title">배송 정보 입력</div>

        <form action="${cp}/order/create" method="post">
            <c:if test="${directOrder}">
                <input type="hidden" name="directIsbn" value="${directIsbn}">
                <input type="hidden" name="directQuantity" value="${directQuantity}">
            </c:if>
            <table class="form-table">
                <tr>
                    <th>수령인</th>
                    <td><input type="text" name="receiverName" class="form-control" required></td>
                </tr>
                <tr>
                    <th>연락처</th>
                    <td><input type="text" name="receiverHp" class="form-control" required></td>
                </tr>
                <tr>
                    <th>주소</th>
                    <td><input type="text" name="deliveryAddress" class="form-control" required></td>
                </tr>
                <tr>
                    <th>상세주소</th>
                    <td><input type="text" name="deliveryDetailAddress" class="form-control" required></td>
                </tr>
            </table>

            <div class="text-right mt-20">
                <a href="${cp}/cart/list" class="btn btn-gray">장바구니로</a>
                <button type="submit" class="btn btn-green">주문완료</button>
            </div>
        </form>
    </div>
</div>

<%@ include file="../include/footer.jsp" %>

</body>
</html>
