	package cart;
	
	import lombok.Data;
	
	@Data // Getter, Setter, ToString 등을 자동으로 생성 (Lombok 사용 시)
	public class CartVO {
	    private int cart_id;      // 장바구니 고유 번호 (PK)
	    private String member_id; // 사용자 아이디 (FK)
	    private int book_id;     // 도서 번호 (FK)
	    private int cart_count;   // 장바구니에 담은 수량
	    
	    // --- 화면에 보여주기 위해 추가로 필요한 필드들 ---
	    private String book_name;  // 도서 제목 (Join 결과 담기용)
	    private int book_price;   // 도서 가격
	    private int total_price;  // 총 금액 (book_price * cart_count)
	    private String image_path; // 도서 이미지 경로
		public void setMemberRank(String memberRank) {
	
			
		}
	}