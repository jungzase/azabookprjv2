package cart;

import java.util.List;

public interface CartService {
    boolean add(Long userId, String isbn, int quantity);
    List<CartVO> getCartList(Long userId);
    boolean update(Long cartId, int quantity, Long userId);
    boolean delete(Long cartId, Long userId);
    boolean clear(Long userId);
}

