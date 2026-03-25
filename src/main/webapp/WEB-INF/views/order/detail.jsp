<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="panel">
	<h2 style="margin:0 0 16px;">주문/배송 조회 상세</h2>
	<div style="padding:18px; border-radius:14px; background:#f5f6fb; font-size:22px; font-weight:bold;">
		${order.orderDate} 주문번호 ${order.orderId}
	</div>
</div>

<div class="panel" style="margin-top:18px;">
	<h3 style="margin-top:0;">배송 상품</h3>
	<c:forEach var="item" items="${order.items}">
		<div style="display:grid; grid-template-columns:120px 1fr 140px 180px; gap:18px; align-items:center; padding:18px 0; border-bottom:1px solid #ececec;">
			<div>
				<a href="${pageContext.request.contextPath}/books/detail?isbn=${item.isbn}">
					<c:choose>
						<c:when test="${empty item.bookImage}">
							<div class="book-thumb"></div>
						</c:when>
						<c:otherwise>
							<img class="book-thumb" src="${item.bookImage}" alt="${item.bookTitle}">
						</c:otherwise>
					</c:choose>
				</a>
			</div>
			<div>
				<div style="font-size:22px; font-weight:bold; margin-bottom:8px;">
					<a href="${pageContext.request.contextPath}/books/detail?isbn=${item.isbn}">${item.bookTitle}</a>
				</div>
				<div class="book-meta">수량 : ${item.quantity}</div>
			</div>
			<div style="font-size:18px; font-weight:bold;">${item.orderPrice}원</div>
			<div>
				<div style="font-weight:bold; color:#3b5ccc; margin-bottom:6px;">${order.statusLabel}</div>
				<div class="book-meta">${order.orderDate}</div>
			</div>
		</div>
	</c:forEach>
</div>

<div class="panel" style="margin-top:18px;">
	<h3 style="margin-top:0;">배송정보</h3>
	<div style="display:grid; grid-template-columns:140px 1fr; gap:12px; line-height:1.9;">
		<div style="font-weight:bold;">기본정보</div>
		<div>${order.receiverName} / ${order.receiverHp}</div>
		<div style="font-weight:bold;">배송지</div>
		<div>${order.deliveryAddress} ${order.deliveryDetailAddress}</div>
	</div>
</div>

<div class="panel" style="margin-top:18px;">
	<h3 style="margin-top:0;">결제 정보</h3>
	<div style="display:grid; grid-template-columns:repeat(3, 1fr); gap:12px; text-align:center;">
		<div>
			<div style="font-weight:bold; margin-bottom:8px;">주문 금액</div>
			<div style="font-size:24px; font-weight:bold;">${order.totalPrice}원</div>
		</div>
		<div>
			<div style="font-weight:bold; margin-bottom:8px;">주문 상태</div>
			<div style="font-size:24px; font-weight:bold;">${order.statusLabel}</div>
		</div>
		<div>
			<div style="font-weight:bold; margin-bottom:8px;">결제 금액</div>
			<div style="font-size:24px; font-weight:bold;">${order.totalPrice}원</div>
		</div>
	</div>
</div>

<div class="inline-actions">
	<a class="btn" href="${pageContext.request.contextPath}/order/history">주문/배송 목록</a>
</div>
