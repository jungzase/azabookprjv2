package book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import common.config.DBConfig;

@Repository
public class BookDAO {

	public List<BookVO> recommendBooks() {
		String sql = "SELECT * FROM books ORDER BY id ASC LIMIT 3";
		List<BookVO> books = new ArrayList<BookVO>();

		try (Connection conn = DBConfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				books.add(toBook(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return books;
	}

	public List<BookVO> search(String keyword, String category) {
		String normalizedKeyword = keyword == null ? "" : keyword.trim().toLowerCase();
		String normalizedCategory = category == null ? "" : category.trim();
		boolean hasKeyword = normalizedKeyword.length() > 0;
		boolean hasCategory = normalizedCategory.length() > 0;

		StringBuilder sql = new StringBuilder("SELECT * FROM books WHERE 1=1");
		List<Object> params = new ArrayList<Object>();

		if (hasKeyword) {
			sql.append(" AND (LOWER(product_name) LIKE ? OR LOWER(author) LIKE ?)");
			String like = "%" + normalizedKeyword + "%";
			params.add(like);
			params.add(like);
		}

		if (hasCategory) {
			sql.append(" AND category = ?");
			params.add(normalizedCategory);
		}

		sql.append(" ORDER BY id ASC");

		List<BookVO> books = new ArrayList<BookVO>();

		try (Connection conn = DBConfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql.toString())) {
			for (int i = 0; i < params.size(); i++) {
				ps.setObject(i + 1, params.get(i));
			}

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					books.add(toBook(rs));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return books;
	}

	public BookVO findByIsbn(String isbn) {
		String sql = "SELECT * FROM books WHERE isbn = ?";

		try (Connection conn = DBConfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, isbn);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return toBook(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public void insert(BookVO book) {
		String sql = "INSERT INTO books (isbn, product_name, author, publisher, publication_date, price, category, img_link) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = DBConfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			fillInsertStatement(ps, book);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(BookVO book) {
		String sql = "UPDATE books SET product_name=?, author=?, publisher=?, publication_date=?, price=?, category=?, img_link=? "
				+ "WHERE isbn=?";

		try (Connection conn = DBConfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, book.getProductName());
			ps.setString(2, book.getAuthor());
			ps.setString(3, book.getPublisher());
			ps.setDate(4, book.getPublicationDate());
			ps.setInt(5, book.getPrice());
			ps.setString(6, book.getCategory());
			ps.setString(7, book.getImgLink());
			ps.setString(8, book.getIsbn());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(String isbn) {
		String sql = "DELETE FROM books WHERE isbn = ?";

		try (Connection conn = DBConfig.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, isbn);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fillInsertStatement(PreparedStatement ps, BookVO book) throws Exception {
		ps.setString(1, book.getIsbn());
		ps.setString(2, book.getProductName());
		ps.setString(3, book.getAuthor());
		ps.setString(4, book.getPublisher());
		ps.setDate(5, book.getPublicationDate());
		ps.setInt(6, book.getPrice());
		ps.setString(7, book.getCategory());
		ps.setString(8, book.getImgLink());
	}

	private BookVO toBook(ResultSet rs) throws Exception {
		BookVO book = new BookVO();
		book.setId(rs.getInt("id"));
		book.setProductName(rs.getString("product_name"));
		book.setIsbn(rs.getString("isbn"));
		book.setAuthor(rs.getString("author"));
		book.setPublisher(rs.getString("publisher"));
		book.setPublicationDate(rs.getDate("publication_date"));
		book.setPrice(rs.getInt("price"));
		book.setCategory(rs.getString("category"));
		book.setImgLink(rs.getString("img_link"));
		return book;
	}
}
