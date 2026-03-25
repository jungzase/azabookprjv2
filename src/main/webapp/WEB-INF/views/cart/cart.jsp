<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <h2>내 장바구니</h2>
    <table class="table">
        <thead>
            <tr>
                <th><input type="checkbox" id="allCheck"></th>
                <th>상품정보</th>
                <th>단가</th>
                <th>수량</th>
                <th>금액</th>
                <th>삭제</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${cartList}">
                <tr>
                    <td><input type="checkbox" name="cart_id" value="${item.cart_id}"></td>
                    <td>
                        <img src="${item.image_path}" width="50">
                        <span>${item.book_name}</span>
                    </td>
                    <td><fmt:formatNumber value="${item.book_price}" pattern="#,###"/>원</td>
                    <td>
                        <input type="number" name="cart_count" value="${item.cart_count}" min="1" style="width: 50px;">
                        <button type="button" class="btn-update" data-id="${item.cart_id}">수정</button>
                    </td>
                    <td><fmt:formatNumber value="${item.total_price}" pattern="#,###"/>원</td>
                    <td>
                        <a href="/cart/delete?cartId=${item.cart_id}" class="btn btn-danger btn-sm">삭제</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <div class="total-area">
        <h3>최종 결제 금액: <span id="finalPrice">0</span>원</h3>
        <button class="btn btn-primary">주문하기</button>
    </div>
</div>