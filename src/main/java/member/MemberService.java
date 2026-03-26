package member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired
	private MemberDAO memberDAO;

	public boolean join(MemberVO member) {
		if (member == null || member.getUsername() == null || member.getUsername().trim().length() == 0) {
			return false;
		}

		if (memberDAO.existsByUsername(member.getUsername())) {
			return false;
		}

		if (member.getRole() == null || member.getRole().trim().length() == 0) {
			member.setRole("USER");
		}

		return memberDAO.insertMember(member) > 0;
	}

	public MemberVO login(String username, String password) {
		if (username == null || password == null) {
			return null;
		}

		return memberDAO.findByLogin(username, password);
	}

	public MemberVO getMember(Long userId) {
		return memberDAO.findByUserId(userId);
	}

	public boolean update(MemberVO member) {
		if (member == null || member.getUser_id() == null) {
			return false;
		}

		return memberDAO.updateMember(member) > 0;
	}
	// [추가] 관리자용: 전체 회원 목록 가져오기
		public List<MemberVO> getMemberList() {
			return memberDAO.findAllMembers();
		}
		public MemberVO getMemberById(Long userId) {
			return memberDAO.findMemberById(userId);
		}

		public boolean modifyMemberByAdmin(MemberVO member) {
			return memberDAO.updateMemberByAdmin(member);
		}

		public boolean removeMember(Long userId) {
			return memberDAO.deleteMember(userId);
		}
}
