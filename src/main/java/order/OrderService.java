package order;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

	@Autowired
	private OrderDAO orderDAO;

	public List<OrderVO> getOrders(Long userId, LocalDate startDate, LocalDate endDate) {
		return orderDAO.findOrdersByUser(userId, startDate, endDate);
	}

	public OrderVO getOrderDetail(Long orderId, Long userId) {
		return orderDAO.findOrderDetail(orderId, userId);
	}

	public Map<Integer, Integer> getStatusCounts(List<OrderVO> orders) {
		Map<Integer, Integer> counts = new LinkedHashMap<Integer, Integer>();
		counts.put(Integer.valueOf(0), Integer.valueOf(0));
		counts.put(Integer.valueOf(1), Integer.valueOf(0));
		counts.put(Integer.valueOf(2), Integer.valueOf(0));
		counts.put(Integer.valueOf(3), Integer.valueOf(0));
		counts.put(Integer.valueOf(4), Integer.valueOf(0));

		for (OrderVO order : orders) {
			Integer key = Integer.valueOf(order.getOrderStatus());
			counts.put(key, Integer.valueOf(counts.get(key).intValue() + 1));
		}

		return counts;
	}
	// 관리자용: 주문 내역 검색
		public List<OrderVO> getAdminOrders(String searchUserId) {
			return orderDAO.findAllOrdersForAdmin(searchUserId);
		}
		
		public OrderVO getOrderDetailAdmin(Long orderId) {
		    return orderDAO.findOrderDetailAdmin(orderId);
		}
		
		public void modifyOrderStatus(Long orderId, int status) {
			orderDAO.updateOrderStatus(orderId, status);
		}
}
