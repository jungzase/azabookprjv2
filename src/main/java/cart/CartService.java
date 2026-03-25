package cart;

import java.util.List;

public interface CartService {

	// 1. 장바구니 추가 (중복 체크 로직 포함)
    public int addCart1(CartVO cart);

    // 2. 장바구니 목록 조회
    public List<CartVO> getCartList(String memberId);

    // 3. 수량 수정
    public int modifyCount1(CartVO cart);

    // 4. 장바구니 삭제
    public int deleteCart1(int cartRank);

    // 5. 장바구니 확인 (이미 담긴 상품인지 체크)
    public CartVO checkCart(CartVO cart);
    
	public void modifyCount(CartVO cart);

	public void addCart(CartVO cart);

	public void deleteCart(int cartRank);

}
