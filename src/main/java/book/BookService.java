package book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

	@Autowired
	private BookDAO bookDAO;

	public List<BookVO> recommendBooks() {
		return bookDAO.recommendBooks();
	}

	public List<BookVO> search(String keyword, String category) {
		return bookDAO.search(keyword, category);
	}

	public BookVO getBookDetail(String isbn) {
		return bookDAO.findByIsbn(isbn);
	}

	public void save(BookVO book, boolean update) {
		if (update) {
			bookDAO.update(book);
			return;
		}

		bookDAO.insert(book);
	}

	public void delete(String isbn) {
		bookDAO.delete(isbn);
	}
}
