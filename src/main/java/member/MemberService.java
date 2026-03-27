package member;

import java.util.List;

public interface MemberService {
    boolean join(MemberVO vo);
    MemberVO login(String username, String password);
    MemberVO getMember(Long userId);
    boolean update(MemberVO vo);
    List<MemberVO> getAllMembers();
}

