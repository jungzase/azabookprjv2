<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 - 주문관리</title>
<link rel="stylesheet" href="${cp}/resources/css/style.css">
</head>
<body>

<%@ include file="../include/header.jsp" %>

<div class="main">
    <div class="flex-between mb-20">
        <div class="section-title" style="margin-bottom:0;">관리자 - 주문 관리</div>
        <div>
            <a href="${cp}/admin/books" class="btn btn-gray">도서 관리</a>
            <a href="${cp}/admin/members" class="btn btn-primary">회원 관리</a>
        </div>
    </div>

    <c:forEach var="o" items="${orderList}">
        <div class="order-card">
            <div class="order-card-top">
                <div>
                    <div><strong>주문번호</strong> #${o.orderId}</div>
                    <div style="margin-top:6px; color:#6b7280;">
                        주문회원 : ${o.username} / 주문일시 : ${o.orderDate}
                    </div>
                </div>

                <form action="${cp}/admin/order/status" method="post" style="display:flex; gap:8px; align-items:center; margin:0;">
                    <input type="hidden" name="orderId" value="${o.orderId}">
                    <select name="orderStatus" class="form-control" style="width:160px;">
                        <option value="0" ${o.orderStatus == 0 ? 'selected' : ''}>주문완료</option>
                        <option value="1" ${o.orderStatus == 1 ? 'selected' : ''}>상품준비중</option>
                        <option value="2" ${o.orderStatus == 2 ? 'selected' : ''}>배송중</option>
                        <option value="3" ${o.orderStatus == 3 ? 'selected' : ''}>배송완료</option>
                        <option value="4" ${o.orderStatus == 4 ? 'selected' : ''}>주문취소</option>
                    </select>
                    <button type="submit" class="btn btn-green">상태변경</button>
                </form>
            </div>

            <div style="line-height:1.9; color:#374151; margin-bottom:14px;">
                <div>수령인 : ${o.receiverName}</div>
                <div>연락처 : ${o.receiverHp}</div>
                <div>배송지 : ${o.deliveryAddress} ${o.deliveryDetailAddress}</div>
                <div><strong>총금액 : ${o.totalPrice}원</strong></div>
            </div>

            <div>
                <strong>주문 상품</strong>
                <div style="margin-top:10px;">
                    <c:forEach var="item" items="${o.items}">
                        <div style="padding:10px 0; border-bottom:1px solid #eceff3;">
                            ${item.bookName} / ${item.quantity}권 / ${item.orderPrice}원
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<%@ include file="../include/footer.jsp" %>

</body>
</html>