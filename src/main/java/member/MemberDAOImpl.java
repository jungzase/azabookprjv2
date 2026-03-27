package member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<MemberVO> mapper = new RowMapper<MemberVO>() {
        @Override
        public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
            MemberVO vo = new MemberVO();
            vo.setUserId(rs.getLong("user_id"));
            vo.setUsername(rs.getString("username"));
            vo.setPassword(rs.getString("password"));
            vo.setName(rs.getString("name"));
            vo.setEmail(rs.getString("email"));
            vo.setHp(rs.getString("hp"));
            vo.setAddress(rs.getString("address"));
            vo.setRole(rs.getString("role"));
            vo.setRegDate(rs.getTimestamp("reg_date"));
            return vo;
        }
    };

    @Override
    public int insert(MemberVO vo) {
        String sql = "INSERT INTO users(username,password,name,email,hp,address,role) VALUES(?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,
                vo.getUsername(), vo.getPassword(), vo.getName(),
                vo.getEmail(), vo.getHp(), vo.getAddress(), "USER");
    }

    @Override
    public MemberVO login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username=? AND password=?";
        List<MemberVO> list = jdbcTemplate.query(sql, mapper, username, password);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public MemberVO findById(Long userId) {
        String sql = "SELECT * FROM users WHERE user_id=?";
        List<MemberVO> list = jdbcTemplate.query(sql, mapper, userId);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public MemberVO findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username=?";
        List<MemberVO> list = jdbcTemplate.query(sql, mapper, username);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public int update(MemberVO vo) {
        String sql = "UPDATE users SET password=?, name=?, email=?, hp=?, address=? WHERE user_id=?";
        return jdbcTemplate.update(sql,
                vo.getPassword(), vo.getName(), vo.getEmail(),
                vo.getHp(), vo.getAddress(), vo.getUserId());
    }

    @Override
    public List<MemberVO> findAll() {
        String sql = "SELECT * FROM users ORDER BY user_id DESC";
        return jdbcTemplate.query(sql, mapper);
    }
}

