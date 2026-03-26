<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="panel">
	<h2 style="margin-top:0;">회원 관리</h2>
	<p>가입된 전체 회원 목록입니다.</p>
</div>

<div class="panel" style="margin-top:18px;">
	<table style="width:100%; text-align:left; border-collapse:collapse;">
		<thead>
			<tr style="border-bottom:2px solid #333; height:40px;">
				<th>회원번호</th>
				<th>아이디</th>
				<th>이름</th>
				<th>이메일</th>
				<th>전화번호</th>
				<th>가입일</th>
				<th>권한</th>
				<th style="text-align:center;">관리</th> </tr>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${empty memberList}">
					<tr>
						<td colspan="7" style="text-align:center; padding:20px;">가입된 회원이 없습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="member" items="${memberList}">
						<tr style="border-bottom:1px solid #ececec; height:50px;">
							<td>${member.user_id}</td>
							<td style="font-weight:bold;">${member.username}</td>
							<td>${member.name}</td>
							<td>${member.email}</td>
							<td>${member.hp}</td>
							<td>${member.reg_date}</td>
							<td>
								<span style="padding:4px 8px; border-radius:4px; font-size:12px; color:#fff; background-color:${member.role == 'ADMIN' ? '#e74c3c' : '#3b5ccc'};">
									${member.role}
								</span>
							</td>
							<td style="text-align:center;">
															<a href="${pageContext.request.contextPath}/admin/members/edit?userId=${member.user_id}" 
															   class="btn" style="padding:4px 8px; font-size:12px; text-decoration:none;">수정</a>
															
															<a href="${pageContext.request.contextPath}/admin/members/delete?userId=${member.user_id}" 
															   class="btn" 
															   style="padding:4px 8px; font-size:12px; background-color:#e74c3c; color:white; text-decoration:none;"
															   onclick="return confirm('${member.username} 회원을 정말 삭제하시겠습니까?');">삭제</a>
														</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</div>