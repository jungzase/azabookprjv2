package member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO {
	private String memberId;
	private String password;
	private String memberName;
	private String email;
	private String phone;
	private String address;
}
