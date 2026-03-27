package member;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO {
    private Long userId;
    private String username;
    private String password;
    private String name;
    private String email;
    private String hp;
    private String address;
    private String role;
    private Timestamp regDate;


}

