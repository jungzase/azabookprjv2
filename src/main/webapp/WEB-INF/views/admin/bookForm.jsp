<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 - 도서 등록/수정</title>
<link rel="stylesheet" href="${cp}/resources/css/style.css">
</head>
<body>

<%@ include file="../include/header.jsp" %>

<div class="main">
    <div class="section-title">관리자 - 도서 등록 / 수정</div>

    <div class="panel">
        <form action="${cp}/admin/book/save" method="post">
            <table class="form-table">
                <tr>
                    <th>ISBN</th>
                    <td><input type="text" name="isbn" value="${book.isbn}" class="form-control"></td>
                </tr>
                <tr>
                    <th>도서명</th>
                    <td><input type="text" name="bookName" value="${book.bookName}" class="form-control"></td>
                </tr>
                <tr>
                    <th>저자</th>
                    <td><input type="text" name="author" value="${book.author}" class="form-control"></td>
                </tr>
                <tr>
                    <th>출판사</th>
                    <td><input type="text" name="publisher" value="${book.publisher}" class="form-control"></td>
                </tr>
                <tr>
                    <th>출간일</th>
                    <td><input type="date" name="publicationDate" value="${book.publicationDate}" class="form-control"></td>
                </tr>
                <tr>
                    <th>가격</th>
                    <td><input type="number" name="price" value="${book.price}" class="form-control"></td>
                </tr>
                <tr>
                    <th>재고</th>
                    <td><input type="number" name="stock" value="${book.stock}" class="form-control"></td>
                </tr>
                <tr>
                    <th>카테고리ID</th>
                    <td>
                        <select name="categoryId" class="form-control">
                            <option value="1" ${book.categoryId == 1 ? 'selected' : ''}>소설</option>
                            <option value="2" ${book.categoryId == 2 ? 'selected' : ''}>IT</option>
                            <option value="3" ${book.categoryId == 3 ? 'selected' : ''}>자기계발</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>이미지 경로</th>
                    <td><input type="text" name="imgLink" value="${book.imgLink}" class="form-control"></td>
                </tr>
                <tr>
                    <th>설명</th>
                    <td><textarea name="description" class="form-control">${book.description}</textarea></td>
                </tr>
                <tr>
                    <th>순위</th>
                    <td><input type="number" name="rankNum" value="${book.rankNum}" class="form-control"></td>
                </tr>
            </table>

            <div class="text-right mt-20">
                <a href="${cp}/admin/books" class="btn btn-gray">목록으로</a>
                <button type="submit" class="btn btn-green">저장</button>
            </div>
        </form>
    </div>
</div>

<%@ include file="../include/footer.jsp" %>

</body>
</html>