<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="panel">
	<div style="display:flex; align-items:center; justify-content:space-between; gap:16px; flex-wrap:wrap;">
		<div>
			<h2 style="margin:0 0 8px;">전체 주문 관리 (Admin)</h2>
			<div style="color:#666;">사이트의 모든 주문을 한눈에 관리하고 유저 아이디로 검색합니다.</div>
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

<div class="panel" style="margin-top:18px; padding:0;"> <table style="width:100%; border-collapse:collapse; text-align:center;">
		<thead>
			<tr style="background-color:#f8f9fa; border-bottom:2px solid #ddd; height:50px;">
				<th style="width:80px;">번호</th>
				<th style="width:120px;">주문일자</th>
				<th style="width:120px;">주문자 ID</th>
				<th style="text-align:left; padding-left:20px;">주문 내용</th>
				<th style="width:120px;">총 결제금액</th>
				<th style="width:120px;">상태</th>
				<th style="width:100px;">상세보기</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${empty orders}">
					<tr>
						<td colspan="7" style="padding:50px; color:#999;">들어온 주문 내역이 없습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="order" items="${orders}">
						<tr style="border-bottom:1px solid #eee; height:60px;" onmouseover="this.style.backgroundColor='#fcfcfc'" onmouseout="this.style.backgroundColor='transparent'">
							<td>${order.orderId}</td>
							<td style="color:#666; font-size:14px;">${order.orderDate}</td>
							<td style="font-weight:bold;">${order.username}</td>
							<td style="text-align:left; padding-left:20px;">
								<a href="${pageContext.request.contextPath}/order/detail?orderId=${order.orderId}" style="text-decoration:none; color:#333;">
									<span style="font-weight:bold;">${order.items[0].bookTitle}</span>
									<c:if test="${order.items.size() > 1}">
										<span style="color:#3b5ccc; font-size:13px;"> 외 ${order.items.size() - 1}건</span>
									</c:if>
								</a>
							</td>
							<td style="font-weight:bold;">
								<fmt:formatNumber value="${order.totalPrice}" pattern="#,###"/>원
							</td>
							<td>
								<span style="padding:4px 8px; border-radius:4px; font-size:12px; color:#fff; 
									background-color: ${order.orderStatus == 4 ? '#999' : (order.orderStatus == 3 ? '#2ecc71' : '#3b5ccc')}">
									${order.statusLabel}
								</span>
							</td>
							<td>
							    <form action="${pageContext.request.contextPath}/admin/orders/updateStatus" method="post" style="display:flex; gap:4px; justify-content:center; align-items:center;">
							        <input type="hidden" name="orderId" value="${order.orderId}">
							        <select name="status" style="padding:4px; border-radius:4px; font-size:12px; border:1px solid #ccc;">
							            <option value="0" ${order.orderStatus == 0 ? 'selected' : ''}>주문완료</option>
							            <option value="1" ${order.orderStatus == 1 ? 'selected' : ''}>상품준비중</option>
							            <option value="2" ${order.orderStatus == 2 ? 'selected' : ''}>배송중</option>
							            <option value="3" ${order.orderStatus == 3 ? 'selected' : ''}>배송완료</option>
							            <option value="4" ${order.orderStatus == 4 ? 'selected' : ''}>주문취소</option>
							        </select>
							        <button type="submit" class="btn primary" style="padding:4px 8px; font-size:11px;">변경</button>
							    </form>
							</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</div>