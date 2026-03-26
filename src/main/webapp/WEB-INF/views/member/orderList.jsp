<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문내역</title>
<link rel="stylesheet" href="${cp}/resources/css/style.css">
</head>
<body>

<%@ include file="../include/header.jsp" %>

<div class="main">
    <div class="section-title">주문내역</div>

    <c:choose>
        <c:when test="${empty orderList}">
            <div class="empty-box">주문내역이 없습니다.</div>
        </c:when>
        <c:otherwise>
            <c:forEach var="o" items="${orderList}">
                <div class="order-card">
                    <div class="order-card-top">
                        <div>
                            <div><strong>주문번호</strong> #${o.orderId}</div>
                            <div style="margin-top:6px; color:#6b7280;">주문일시 : ${o.orderDate}</div>
                        </div>
                        <div>
                            <span class="badge badge-${o.orderStatus}">
                                <c:choose>
                                    <c:when test="${o.orderStatus == 0}">주문완료</c:when>
                                    <c:when test="${o.orderStatus == 1}">상품준비중</c:when>
                                    <c:when test="${o.orderStatus == 2}">배송중</c:when>
                                    <c:when test="${o.orderStatus == 3}">배송완료</c:when>
                                    <c:when test="${o.orderStatus == 4}">주문취소</c:when>
                                    <c:otherwise>상태확인</c:otherwise>
                                </c:choose>
                            </span>
                        </div>
                    </div>

                    <div style="line-height:1.9; color:#374151; margin-bottom:14px;">
                        <div>수령인 : ${o.receiverName}</div>
                        <div>연락처 : ${o.receiverHp}</div>
                        <div>배송지 : ${o.deliveryAddress} ${o.deliveryDetailAddress}</div>
                        <div><strong>총 결제금액 : ${o.totalPrice}원</strong></div>
                    </div>

                    <div>
                        <strong>주문상품</strong>
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
        </c:otherwise>
    </c:choose>
</div>

<%@ include file="../include/footer.jsp" %>

</body>
</html>