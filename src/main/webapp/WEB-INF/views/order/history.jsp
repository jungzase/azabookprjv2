<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="panel">
	<div style="display:flex; align-items:center; justify-content:space-between; gap:16px; flex-wrap:wrap;">
		<div>
			<h2 style="margin:0 0 8px;">주문/배송 목록</h2>
			<div style="color:#666;">기본 조회 기간은 최근 6개월입니다.</div>
		</div>
		<form action="${pageContext.request.contextPath}/order/history" method="get" style="display:flex; gap:8px; flex-wrap:wrap; align-items:end;">
			<div class="form-row" style="margin-bottom:0;">
				<label for="startDate">시작일</label>
				<input type="date" id="startDate" name="startDate" value="${startDate}">
			</div>
			<div class="form-row" style="margin-bottom:0;">
				<label for="endDate">종료일</label>
				<input type="date" id="endDate" name="endDate" value="${endDate}">
			</div>
			<button type="submit" class="btn">상세조회</button>
		</form>
	</div>
</div>

<c:choose>
	<c:when test="${empty orders}">
		<div class="placeholder" style="margin-top:18px;">선택한 기간의 주문내역이 없습니다.</div>
	</c:when>
	<c:otherwise>
		<c:forEach var="order" items="${orders}">
			<div class="panel" style="margin-top:18px;">
				<div style="display:flex; align-items:center; justify-content:space-between; gap:12px; flex-wrap:wrap; padding-bottom:14px; border-bottom:1px solid #e5e5e5;">
					<div style="font-size:22px; font-weight:bold;">${order.orderDate} (주문번호 ${order.orderId})</div>
					<a href="${pageContext.request.contextPath}/order/detail?orderId=${order.orderId}" class="btn">상세보기</a>
				</div>
				<c:forEach var="item" items="${order.items}">
					<div style="display:grid; grid-template-columns:120px 1fr 160px 160px; gap:18px; align-items:center; padding:20px 0; border-bottom:1px solid #f0f0f0;">
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
							<div style="font-size:26px; font-weight:bold; margin-bottom:10px;">
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
		</c:forEach>
	</c:otherwise>
</c:choose>
