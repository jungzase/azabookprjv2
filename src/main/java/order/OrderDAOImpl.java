package order;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDAOImpl implements OrderDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Long insertOrder(OrderVO order) {
        String sql = "INSERT INTO orders(user_id,order_status,total_price,receiver_name,receiver_hp,delivery_address,delivery_detail_address) "
                   + "VALUES(?,?,?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[] { "order_id" });
            ps.setLong(1, order.getUserId());
            ps.setInt(2, order.getOrderStatus());
            ps.setInt(3, order.getTotalPrice());
            ps.setString(4, order.getReceiverName());
            ps.setString(5, order.getReceiverHp());
            ps.setString(6, order.getDeliveryAddress());
            ps.setString(7, order.getDeliveryDetailAddress());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key != null) {
            return key.longValue();
        }

        Map<String, Object> keys = keyHolder.getKeys();
        if (keys != null) {
            Object orderIdObj = keys.get("ORDER_ID");
            if (orderIdObj == null) {
                orderIdObj = keys.get("order_id");
            }
            if (orderIdObj instanceof Number) {
                return ((Number) orderIdObj).longValue();
            }
        }

        throw new RuntimeException("주문번호 생성에 실패했습니다.");
    }

    @Override
    public int insertOrderItem(OrderItemVO item) {
        String sql = "INSERT INTO order_items(order_id,isbn,quantity,order_price) VALUES(?,?,?,?)";
        return jdbcTemplate.update(sql,
                item.getOrderId(),
                item.getIsbn(),
                item.getQuantity(),
                item.getOrderPrice());
    }

    @Override
    public List<OrderVO> findByUserId(Long userId) {
        String sql = "SELECT o.*, u.username "
                   + "FROM orders o "
                   + "JOIN users u ON o.user_id = u.user_id "
                   + "WHERE o.user_id = ? "
                   + "ORDER BY o.order_id DESC";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            OrderVO vo = new OrderVO();
            vo.setOrderId(rs.getLong("order_id"));
            vo.setUserId(rs.getLong("user_id"));
            vo.setUsername(rs.getString("username"));
            vo.setOrderDate(rs.getTimestamp("order_date"));
            vo.setOrderStatus(rs.getInt("order_status"));
            vo.setTotalPrice(rs.getInt("total_price"));
            vo.setReceiverName(rs.getString("receiver_name"));
            vo.setReceiverHp(rs.getString("receiver_hp"));
            vo.setDeliveryAddress(rs.getString("delivery_address"));
            vo.setDeliveryDetailAddress(rs.getString("delivery_detail_address"));
            return vo;
        }, userId);
    }

    @Override
    public List<OrderVO> findAll() {
        String sql = "SELECT o.*, u.username "
                   + "FROM orders o "
                   + "JOIN users u ON o.user_id = u.user_id "
                   + "ORDER BY o.order_id DESC";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            OrderVO vo = new OrderVO();
            vo.setOrderId(rs.getLong("order_id"));
            vo.setUserId(rs.getLong("user_id"));
            vo.setUsername(rs.getString("username"));
            vo.setOrderDate(rs.getTimestamp("order_date"));
            vo.setOrderStatus(rs.getInt("order_status"));
            vo.setTotalPrice(rs.getInt("total_price"));
            vo.setReceiverName(rs.getString("receiver_name"));
            vo.setReceiverHp(rs.getString("receiver_hp"));
            vo.setDeliveryAddress(rs.getString("delivery_address"));
            vo.setDeliveryDetailAddress(rs.getString("delivery_detail_address"));
            return vo;
        });
    }

    @Override
    public List<OrderItemVO> findItemsByOrderId(Long orderId) {
        String sql = "SELECT oi.*, b.book_name "
                   + "FROM order_items oi "
                   + "JOIN books b ON oi.isbn = b.isbn "
                   + "WHERE oi.order_id = ?";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            OrderItemVO vo = new OrderItemVO();
            vo.setOrderItemId(rs.getLong("order_item_id"));
            vo.setOrderId(rs.getLong("order_id"));
            vo.setIsbn(rs.getString("isbn"));
            vo.setBookName(rs.getString("book_name"));
            vo.setQuantity(rs.getInt("quantity"));
            vo.setOrderPrice(rs.getInt("order_price"));
            return vo;
        }, orderId);
    }

    @Override
    public int updateStatus(Long orderId, int orderStatus) {
        String sql = "UPDATE orders SET order_status = ? WHERE order_id = ?";
        return jdbcTemplate.update(sql, orderStatus, orderId);
    }
}

