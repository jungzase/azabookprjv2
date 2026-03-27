package member;

import java.util.List;

public interface MemberDAO {
    int insert(MemberVO vo);
    MemberVO login(String username, String password);
    MemberVO findById(Long userId);
    MemberVO findByUsername(String username);
    int update(MemberVO vo);
    List<MemberVO> findAll();
}

