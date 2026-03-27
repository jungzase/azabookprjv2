package cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDAO cartDAO;

    @Override
    public boolean add(Long userId, String isbn, int quantity) {
        return cartDAO.addOrIncrease(userId, isbn, quantity) > 0;
    }

    @Override
    public List<CartVO> getCartList(Long userId) {
        return cartDAO.findByUserId(userId);
    }

    @Override
    public boolean update(Long cartId, int quantity, Long userId) {
        return cartDAO.updateQuantity(cartId, quantity, userId) > 0;
    }

    @Override
    public boolean delete(Long cartId, Long userId) {
        return cartDAO.delete(cartId, userId) > 0;
    }

    @Override
    public boolean clear(Long userId) {
        return cartDAO.clear(userId) > 0;
    }
}

