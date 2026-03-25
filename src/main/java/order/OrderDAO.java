package order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import common.config.DBConfig;

@Repository
public class OrderDAO {

	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy.MM.dd");

	public List<OrderVO> findOrdersByUser(Long userId, LocalDate startDate, LocalDate endDate) {
		String sql = "SELECT o.order_id, o.user_id, o.order_date, o.order_status, o.total_price, "
				+ "o.receiver_name, o.receiver_hp, o.delivery_address, o.delivery_detail_address, "
				+ "oi.order_item_id, oi.isbn, oi.quantity, oi.order_price, "
				+ "b.product_name, b.img_link "
				+ "FROM orders o "
				+ "JOIN order_items oi ON o.order_id = oi.order_id "
				+ "LEFT JOIN books b ON oi.isbn = b.isbn "
				+ "WHERE o.user_id = ? AND o.order_date >= ? AND o.order_date < ? "
				+ "ORDER BY o.order_date DESC, o.order_id DESC, oi.order_item_id ASC";

		Map<Long, OrderVO> orderMap = new LinkedHashMap<Long, OrderVO>();

		try (Connection conn = DBConfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setLong(1, userId.longValue());
			ps.setTimestamp(2, Timestamp.valueOf(startDate.atStartOfDay()));
			ps.setTimestamp(3, Timestamp.valueOf(endDate.plusDays(1).atStartOfDay()));

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Long orderId = Long.valueOf(rs.getLong("order_id"));
					OrderVO order = orderMap.get(orderId);

					if (order == null) {
						order = toOrder(rs);
						orderMap.put(orderId, order);
					}

					order.getItems().add(toItem(rs));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ArrayList<OrderVO>(orderMap.values());
	}

	public OrderVO findOrderDetail(Long orderId, Long userId) {
		String sql = "SELECT o.order_id, o.user_id, o.order_date, o.order_status, o.total_price, "
				+ "o.receiver_name, o.receiver_hp, o.delivery_address, o.delivery_detail_address, "
				+ "oi.order_item_id, oi.isbn, oi.quantity, oi.order_price, "
				+ "b.product_name, b.img_link "
				+ "FROM orders o "
				+ "JOIN order_items oi ON o.order_id = oi.order_id "
				+ "LEFT JOIN books b ON oi.isbn = b.isbn "
				+ "WHERE o.order_id = ? AND o.user_id = ? "
				+ "ORDER BY oi.order_item_id ASC";

		OrderVO order = null;

		try (Connection conn = DBConfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setLong(1, orderId.longValue());
			ps.setLong(2, userId.longValue());

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					if (order == null) {
						order = toOrder(rs);
					}

					order.getItems().add(toItem(rs));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return order;
	}

	private OrderVO toOrder(ResultSet rs) throws Exception {
		OrderVO order = new OrderVO();
		order.setOrderId(Long.valueOf(rs.getLong("order_id")));
		order.setUserId(Long.valueOf(rs.getLong("user_id")));
		order.setOrderDate(rs.getTimestamp("order_date").toLocalDateTime().toLocalDate().format(DATE_FORMAT));
		order.setOrderStatus(rs.getInt("order_status"));
		order.setTotalPrice(rs.getInt("total_price"));
		order.setReceiverName(rs.getString("receiver_name"));
		order.setReceiverHp(rs.getString("receiver_hp"));
		order.setDeliveryAddress(rs.getString("delivery_address"));
		order.setDeliveryDetailAddress(rs.getString("delivery_detail_address"));
		return order;
	}

	private OrderItemVO toItem(ResultSet rs) throws Exception {
		OrderItemVO item = new OrderItemVO();
		item.setOrderItemId(Long.valueOf(rs.getLong("order_item_id")));
		item.setOrderId(Long.valueOf(rs.getLong("order_id")));
		item.setIsbn(rs.getString("isbn"));
		item.setQuantity(rs.getInt("quantity"));
		item.setOrderPrice(rs.getInt("order_price"));
		item.setBookTitle(rs.getString("product_name"));
		item.setBookImage(rs.getString("img_link"));
		return item;
	}
}
