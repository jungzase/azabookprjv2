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
				+ "b.book_name, b.img_link "
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
				+ "b.book_name, b.img_link "
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
	
	// 관리자용: 전체 주문 조회 및 유저 아이디 검색
		public List<OrderVO> findAllOrdersForAdmin(String searchUserId) {
			StringBuilder sql = new StringBuilder(
					"SELECT o.order_id, o.user_id, o.order_date, o.order_status, o.total_price, "
					+ "o.receiver_name, o.receiver_hp, o.delivery_address, o.delivery_detail_address, "
					+ "oi.order_item_id, oi.isbn, oi.quantity, oi.order_price, "
					+ "b.book_name, b.img_link, u.username " // users 테이블 조인해서 아이디 가져오기
					+ "FROM orders o "
					+ "JOIN order_items oi ON o.order_id = oi.order_id "
					+ "LEFT JOIN books b ON oi.isbn = b.isbn "
					+ "LEFT JOIN users u ON o.user_id = u.user_id "
			);

			boolean hasSearch = (searchUserId != null && searchUserId.trim().length() > 0);
			if (hasSearch) {
				sql.append("WHERE u.username LIKE ? "); // 아이디로 검색
			}
			sql.append("ORDER BY o.order_date DESC, o.order_id DESC, oi.order_item_id ASC");

			Map<Long, OrderVO> orderMap = new LinkedHashMap<>();

			try (Connection conn = DBConfig.getConnection();
					PreparedStatement ps = conn.prepareStatement(sql.toString())) {
				
				if (hasSearch) {
					ps.setString(1, "%" + searchUserId + "%");
				}

				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						Long orderId = Long.valueOf(rs.getLong("order_id"));
						OrderVO order = orderMap.get(orderId);

						if (order == null) {
							order = adminToOrder(rs); // 관리자용 맵핑 메서드 사용
							orderMap.put(orderId, order);
						}
						order.getItems().add(toItem(rs));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ArrayList<>(orderMap.values());
		}
		
		public OrderVO findOrderDetailAdmin(Long orderId) {
		    String sql = "SELECT o.order_id, o.user_id, o.order_date, o.order_status, o.total_price, "
		            + "o.receiver_name, o.receiver_hp, o.delivery_address, o.delivery_detail_address, "
		            + "oi.order_item_id, oi.isbn, oi.quantity, oi.order_price, "
		            + "b.book_name, b.img_link " // 아까 수정한 book_name 반영
		            + "FROM orders o "
		            + "JOIN order_items oi ON o.order_id = oi.order_id "
		            + "LEFT JOIN books b ON oi.isbn = b.isbn "
		            + "WHERE o.order_id = ? " // user_id 체크를 뺐습니다!
		            + "ORDER BY oi.order_item_id ASC";

		    OrderVO order = null;
		    try (Connection conn = common.config.DBConfig.getConnection();
		            PreparedStatement ps = conn.prepareStatement(sql)) {
		        ps.setLong(1, orderId);
		        try (ResultSet rs = ps.executeQuery()) {
		            while (rs.next()) {
		                if (order == null) order = toOrder(rs);
		                order.getItems().add(toItem(rs));
		            }
		        }
		    } catch (Exception e) { e.printStackTrace(); }
		    return order;
		}
		
		// 관리자용: 주문 상태 변경 기능
		public boolean updateOrderStatus(Long orderId, int status) {
			String sql = "UPDATE orders SET order_status = ? WHERE order_id = ?";
			try (Connection conn = common.config.DBConfig.getConnection();
					PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setInt(1, status);
				ps.setLong(2, orderId);
				return ps.executeUpdate() > 0;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}
	
	private OrderVO adminToOrder(ResultSet rs) throws Exception {
			OrderVO order = toOrder(rs);
			order.setUsername(rs.getString("username")); // 추가된 유저 아이디 세팅
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
		item.setBookTitle(rs.getString("book_name"));
		item.setBookImage(rs.getString("img_link"));
		return item;
	}
}
