<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="panel">
	<div style="display:flex; align-items:center; justify-content:space-between; gap:16px; flex-wrap:wrap;">
		<div>
			<h2 style="margin:0 0 8px;">주문 관리 (관리자)</h2>
			<div style="color:#666;">전체 주문 내역을 확인하고, 유저 ID로 검색할 수 있습니다.</div>
		</div>
		
		<form action="${pageContext.request.contextPath}/admin/orders" method="get" style="display:flex; gap:8px; align-items:end;">
			<div class="form-row" style="margin-bottom:0;">
				<label for="searchUserId">유저 ID 검색</label>
				<input type="text" id="searchUserId" name="searchUserId" value="${searchUserId}" placeholder="아이디 입력">
			</div>
			<button type="submit" class="btn primary">검색</button>
			<a href="${pageContext.request.contextPath}/admin/orders" class="btn">초기화</a>
		</form>
	</div>
</div>

<c:choose>
	<c:when test="${empty orders}">
		<div class="placeholder" style="margin-top:18px;">
			<c:if test="${not empty searchUserId}">
				'${searchUserId}' 아이디로 검색된 주문 내역이 없습니다.
			</c:if>
			<c:if test="${empty searchUserId}">
				주문 내역이 없습니다.
			</c:if>
		</div>
	</c:when>
	<c:otherwise>
		<c:forEach var="order" items="${orders}">
			<div class="panel" style="margin-top:18px; border-left:4px solid #3b5ccc;">
				<div style="display:flex; align-items:center; justify-content:space-between; gap:12px; flex-wrap:wrap; padding-bottom:14px; border-bottom:1px solid #e5e5e5;">
					<div style="font-size:20px; font-weight:bold;">
						[주문일: ${order.orderDate}] 
						<span style="color:#e74c3c; margin-left:10px;">주문자 ID: ${order.username}</span> 
						(주문번호: ${order.orderId})
					</div>
					<div style="font-weight:bold; font-size:18px;">
						총 결제금액: <fmt:formatNumber value="${order.totalPrice}" pattern="#,###"/>원
						<span style="display:inline-block; padding:6px 12px; margin-left:10px; background-color:#333; color:white; border-radius:6px; font-size:14px;">
							${order.statusLabel}
						</span>
					</div>
				</div>
				
				<c:forEach var="item" items="${order.items}">
					<div style="display:grid; grid-template-columns:80px 1fr 120px 120px; gap:18px; align-items:center; padding:12px 0; border-bottom:1px dashed #f0f0f0;">
						<div>
							<c:choose>
								<c:when test="${empty item.bookImage}">
									<div class="book-thumb" style="width:60px; height:80px;"></div>
								</c:when>
								<c:otherwise>
									<img src="${item.bookImage}" style="width:60px; height:80px; object-fit:cover;">
								</c:otherwise>
							</c:choose>
						</div>
						<div>
							<div style="font-size:18px; font-weight:bold; margin-bottom:6px;">${item.bookTitle}</div>
							<div style="color:#666;">ISBN: ${item.isbn}</div>
						</div>
						<div style="text-align:right;">수량: ${item.quantity}개</div>
						<div style="font-weight:bold; text-align:right;"><fmt:formatNumber value="${item.orderPrice}" pattern="#,###"/>원</div>
					</div>
				</c:forEach>
			</div>
		</c:forEach>
	</c:otherwise>
</c:choose>