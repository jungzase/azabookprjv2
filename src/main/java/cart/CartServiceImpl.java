package cart;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Override
    public int addCart1(CartVO cart) {
        return 0;
    }

    @Override
    public List<CartVO> getCartList(String memberId) {
        return null;
    }

    @Override
    public int modifyCount1(CartVO cart) {
        return 0;
    }

    @Override
    public int deleteCart1(int cartRank) {
        return 0;
    }

    @Override
    public CartVO checkCart(CartVO cart) {
        return null;
    }

    @Override
    public void modifyCount(CartVO cart) {
        
    }

    @Override
    public void addCart(CartVO cart) {
        
    }

    @Override
    public void deleteCart(int cartRank) {
        
    }
}