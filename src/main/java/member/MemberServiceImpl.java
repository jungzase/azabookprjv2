package member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDAO memberDAO;

    @Override
    public boolean join(MemberVO vo) {
        if (memberDAO.findByUsername(vo.getUsername()) != null) return false;
        return memberDAO.insert(vo) > 0;
    }

    @Override
    public MemberVO login(String username, String password) {
        return memberDAO.login(username, password);
    }

    @Override
    public MemberVO getMember(Long userId) {
        return memberDAO.findById(userId);
    }

    @Override
    public boolean update(MemberVO vo) {
        return memberDAO.update(vo) > 0;
    }

    @Override
    public List<MemberVO> getAllMembers() {
        return memberDAO.findAll();
    }
}

