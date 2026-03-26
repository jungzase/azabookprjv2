package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import common.config.DBConfig;

@Repository
public class MemberDAO {

	public int insertMember(MemberVO member) {
		String sql = "INSERT INTO users (username, password, name, email, hp, address, role, reg_date) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";

		try (Connection conn = DBConfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, member.getUsername());
			ps.setString(2, member.getPassword());
			ps.setString(3, member.getName());
			ps.setString(4, member.getEmail());
			ps.setString(5, member.getHp());
			ps.setString(6, member.getAddress());
			ps.setString(7, member.getRole());
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public MemberVO findByLogin(String username, String password) {
		String sql = "SELECT user_id, username, password, name, email, hp, address, role, reg_date "
				+ "FROM users WHERE username = ? AND password = ?";

		try (Connection conn = DBConfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, username);
			ps.setString(2, password);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return toMember(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public MemberVO findByUserId(Long userId) {
		String sql = "SELECT user_id, username, password, name, email, hp, address, role, reg_date "
				+ "FROM users WHERE user_id = ?";

		try (Connection conn = DBConfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setLong(1, userId.longValue());

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return toMember(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public int updateMember(MemberVO member) {
		String sql = "UPDATE users SET username = ?, password = ?, name = ?, email = ?, hp = ?, address = ? "
				+ "WHERE user_id = ?";

		try (Connection conn = DBConfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, member.getUsername());
			ps.setString(2, member.getPassword());
			ps.setString(3, member.getName());
			ps.setString(4, member.getEmail());
			ps.setString(5, member.getHp());
			ps.setString(6, member.getAddress());
			ps.setLong(7, member.getUser_id().longValue());
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public boolean existsByUsername(String username) {
		String sql = "SELECT COUNT(*) FROM users WHERE username = ?";

		try (Connection conn = DBConfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, username);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	private MemberVO toMember(ResultSet rs) throws Exception {
		MemberVO member = new MemberVO();
		member.setUser_id(rs.getLong("user_id"));
		member.setUsername(rs.getString("username"));
		member.setPassword(rs.getString("password"));
		member.setName(rs.getString("name"));
		member.setEmail(rs.getString("email"));
		member.setHp(rs.getString("hp"));
		member.setAddress(rs.getString("address"));
		member.setRole(rs.getString("role"));
		member.setReg_date(rs.getString("reg_date"));
		return member;
	}
	
	//  관리자용: 전체 회원 목록 조회
		public List<MemberVO> findAllMembers() {
			List<MemberVO> list = new ArrayList<>();
			String sql = "SELECT user_id, username, password, name, email, hp, address, role, reg_date "
					+ "FROM users ORDER BY user_id DESC";

			try (Connection conn = DBConfig.getConnection();
					PreparedStatement ps = conn.prepareStatement(sql);
					ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					list.add(toMember(rs));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
		public MemberVO findMemberById(Long userId) {
			String sql = "SELECT * FROM users WHERE user_id = ?";
			try (Connection conn = DBConfig.getConnection();
					PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setLong(1, userId);
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) return toMember(rs);
				}
			} catch (Exception e) { e.printStackTrace(); }
			return null;
		}
		
		// 관리자용: 회원 정보 수정 (아이디 제외 이름, 이메일, 전화번호, 주소, 권한 수정)
		public boolean updateMemberByAdmin(MemberVO member) {
			String sql = "UPDATE users SET name=?, email=?, hp=?, address=?, role=? WHERE user_id=?";
			try (Connection conn = DBConfig.getConnection();
					PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setString(1, member.getName());
				ps.setString(2, member.getEmail());
				ps.setString(3, member.getHp());
				ps.setString(4, member.getAddress());
				ps.setString(5, member.getRole());
				ps.setLong(6, member.getUser_id());
				return ps.executeUpdate() > 0;
			} catch (Exception e) { e.printStackTrace(); }
			return false;
		}
		
		// 관리자용: 회원 삭제
		public boolean deleteMember(Long userId) {
			String sql = "DELETE FROM users WHERE user_id = ?";
			try (Connection conn = DBConfig.getConnection();
					PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setLong(1, userId);
				return ps.executeUpdate() > 0;
			} catch (Exception e) { e.printStackTrace(); }
			return false;
		}
}
