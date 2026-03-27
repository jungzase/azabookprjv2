package cart;

import java.util.List;

public interface CartDAO {
    int addOrIncrease(Long userId, String isbn, int quantity);
    List<CartVO> findByUserId(Long userId);
    int updateQuantity(Long cartId, int quantity, Long userId);
    int delete(Long cartId, Long userId);
    int clear(Long userId);
}

