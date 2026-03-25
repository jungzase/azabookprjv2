package member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO {
	private Long user_id;
	private String username;
	private String password;
	private String name;
	private String email;
	private String hp;
	private String address;
	private String role;
	private String reg_date;
}
