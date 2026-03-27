package book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookDAOImpl implements BookDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private BookVO map(ResultSet rs) throws SQLException {
        BookVO vo = new BookVO();
        vo.setIsbn(rs.getString("isbn"));
        vo.setBookName(rs.getString("book_name"));
        vo.setAuthor(rs.getString("author"));
        vo.setPublisher(rs.getString("publisher"));
        vo.setPublicationDate(rs.getDate("publication_date"));
        vo.setPrice(rs.getInt("price"));
        vo.setStock(rs.getInt("stock"));
        vo.setCategoryId(rs.getLong("category_id"));
        vo.setCategoryName(rs.getString("category_name"));
        vo.setImgLink(rs.getString("img_link"));
        vo.setDescription(rs.getString("description"));
        vo.setRankNum(rs.getInt("rank_num"));
        return vo;
    }

    @Override
    public List<BookVO> findAll(String keyword, Long categoryId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT b.*, c.category_name FROM books b ");
        sql.append("LEFT JOIN categories c ON b.category_id = c.category_id WHERE 1=1 ");

        List<Object> params = new ArrayList<>();

        if (keyword != null && !keyword.trim().isEmpty()) {
            sql.append("AND (b.book_name LIKE ? OR b.author LIKE ? OR b.publisher LIKE ?) ");
            String k = "%" + keyword + "%";
            params.add(k);
            params.add(k);
            params.add(k);
        }

        if (categoryId != null) {
            sql.append("AND b.category_id = ? ");
            params.add(categoryId);
        }

        sql.append("ORDER BY b.rank_num ASC, b.book_name ASC");

        return jdbcTemplate.query(sql.toString(), (rs, rowNum) -> map(rs), params.toArray());
    }

    @Override
    public List<BookVO> findNewest() {
        String sql = "SELECT b.*, c.category_name "
                   + "FROM books b "
                   + "LEFT JOIN categories c ON b.category_id = c.category_id "
                   + "ORDER BY b.publication_date DESC, b.rank_num ASC, b.book_name ASC";
        return jdbcTemplate.query(sql, (rs, rowNum) -> map(rs));
    }

    @Override
    public List<BookVO> findTopRanked(int limit) {
        String sql = "SELECT b.*, c.category_name "
                   + "FROM books b "
                   + "LEFT JOIN categories c ON b.category_id = c.category_id "
                   + "ORDER BY b.rank_num ASC, b.book_name ASC "
                   + "LIMIT ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> map(rs), limit);
    }

    @Override
    public BookVO findByIsbn(String isbn) {
        String sql = "SELECT b.*, c.category_name FROM books b LEFT JOIN categories c ON b.category_id=c.category_id WHERE b.isbn=?";
        List<BookVO> list = jdbcTemplate.query(sql, (rs, rowNum) -> map(rs), isbn);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public int insert(BookVO vo) {
        String sql = "INSERT INTO books(isbn,book_name,author,publisher,publication_date,price,stock,category_id,img_link,description,rank_num) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,
                vo.getIsbn(), vo.getBookName(), vo.getAuthor(), vo.getPublisher(),
                vo.getPublicationDate(), vo.getPrice(), vo.getStock(),
                vo.getCategoryId(), vo.getImgLink(), vo.getDescription(), vo.getRankNum());
    }

    @Override
    public int update(BookVO vo) {
        String sql = "UPDATE books SET book_name=?, author=?, publisher=?, publication_date=?, price=?, stock=?, category_id=?, img_link=?, description=?, rank_num=? WHERE isbn=?";
        return jdbcTemplate.update(sql,
                vo.getBookName(), vo.getAuthor(), vo.getPublisher(), vo.getPublicationDate(),
                vo.getPrice(), vo.getStock(), vo.getCategoryId(), vo.getImgLink(),
                vo.getDescription(), vo.getRankNum(), vo.getIsbn());
    }

    @Override
    public int delete(String isbn) {
        String sql = "DELETE FROM books WHERE isbn=?";
        return jdbcTemplate.update(sql, isbn);
    }

    @Override
    public List<BookVO> findAllAdmin() {
        return findAll(null, null);
    }

    @Override
    public int updateStock(String isbn, int stock) {
        String sql = "UPDATE books SET stock=? WHERE isbn=?";
        return jdbcTemplate.update(sql, stock, isbn);
    }
}


