package cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CartDAOImpl implements CartDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int addOrIncrease(Long userId, String isbn, int quantity) {
        String checkSql = "SELECT COUNT(*) FROM cart WHERE user_id=? AND isbn=?";
        Integer count = jdbcTemplate.queryForObject(checkSql, Integer.class, userId, isbn);

        if (count != null && count > 0) {
            String updateSql = "UPDATE cart SET quantity = quantity + ? WHERE user_id=? AND isbn=?";
            return jdbcTemplate.update(updateSql, quantity, userId, isbn);
        } else {
            String insertSql = "INSERT INTO cart(user_id,isbn,quantity) VALUES(?,?,?)";
            return jdbcTemplate.update(insertSql, userId, isbn, quantity);
        }
    }

    @Override
    public List<CartVO> findByUserId(Long userId) {
        String sql = "SELECT c.cart_id, c.user_id, c.isbn, c.quantity, b.book_name, b.price, b.stock " +
                     "FROM cart c JOIN books b ON c.isbn=b.isbn WHERE c.user_id=? ORDER BY c.cart_id DESC";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            CartVO vo = new CartVO();
            vo.setCartId(rs.getLong("cart_id"));
            vo.setUserId(rs.getLong("user_id"));
            vo.setIsbn(rs.getString("isbn"));
            vo.setQuantity(rs.getInt("quantity"));
            vo.setBookName(rs.getString("book_name"));
            vo.setPrice(rs.getInt("price"));
            vo.setStock(rs.getInt("stock"));
            return vo;
        }, userId);
    }

    @Override
    public int updateQuantity(Long cartId, int quantity, Long userId) {
        String sql = "UPDATE cart SET quantity=? WHERE cart_id=? AND user_id=?";
        return jdbcTemplate.update(sql, quantity, cartId, userId);
    }

    @Override
    public int delete(Long cartId, Long userId) {
        String sql = "DELETE FROM cart WHERE cart_id=? AND user_id=?";
        return jdbcTemplate.update(sql, cartId, userId);
    }

    @Override
    public int clear(Long userId) {
        String sql = "DELETE FROM cart WHERE user_id=?";
        return jdbcTemplate.update(sql, userId);
    }
}

