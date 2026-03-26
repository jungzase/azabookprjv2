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
                    <th>카테고리</th>
                    <td>
                        <select name="categoryId" class="form-control">
                            <option value="1" ${book.categoryId == 1 ? 'selected' : ''}>소설/시/희곡</option>
                            <option value="2" ${book.categoryId == 2 ? 'selected' : ''}>자기계발</option>
                            <option value="3" ${book.categoryId == 3 ? 'selected' : ''}>경제경영</option>
                            <option value="4" ${book.categoryId == 4 ? 'selected' : ''}>외국어</option>
                            <option value="5" ${book.categoryId == 5 ? 'selected' : ''}>인문학</option>
                            <option value="6" ${book.categoryId == 6 ? 'selected' : ''}>만화/라이트노벨</option>
                            <option value="7" ${book.categoryId == 7 ? 'selected' : ''}>예술/대중문화</option>
                            <option value="8" ${book.categoryId == 8 ? 'selected' : ''}>과학</option>
                            <option value="9" ${book.categoryId == 9 ? 'selected' : ''}>수험서/자격증</option>
                            <option value="10" ${book.categoryId == 10 ? 'selected' : ''}>에세이</option>
                            <option value="11" ${book.categoryId == 11 ? 'selected' : ''}>사회과학</option>
                            <option value="12" ${book.categoryId == 12 ? 'selected' : ''}>어린이</option>
                            <option value="13" ${book.categoryId == 13 ? 'selected' : ''}>건강/취미</option>
                            <option value="14" ${book.categoryId == 14 ? 'selected' : ''}>유아</option>
                            <option value="15" ${book.categoryId == 15 ? 'selected' : ''}>역사</option>
                            <option value="16" ${book.categoryId == 16 ? 'selected' : ''}>종교/역학</option>
                            <option value="17" ${book.categoryId == 17 ? 'selected' : ''}>컴퓨터/모바일</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>이미지 링크</th>
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
