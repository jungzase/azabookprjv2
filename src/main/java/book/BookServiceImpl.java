package book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDAO bookDAO;

    @Override
    public List<BookVO> getBookList(String keyword, Long categoryId) {
        return bookDAO.findAll(keyword, categoryId);
    }

    @Override
    public List<BookVO> getNewestBooks() {
        return bookDAO.findNewest();
    }

    @Override
    public List<BookVO> getTopRankedBooks(int limit) {
        return bookDAO.findTopRanked(limit);
    }

    @Override
    public BookVO getBook(String isbn) {
        return bookDAO.findByIsbn(isbn);
    }

    @Override
    public boolean insert(BookVO vo) {
        return bookDAO.insert(vo) > 0;
    }

    @Override
    public boolean update(BookVO vo) {
        return bookDAO.update(vo) > 0;
    }

    @Override
    public boolean delete(String isbn) {
        return bookDAO.delete(isbn) > 0;
    }

    @Override
    public List<BookVO> getAllBooksAdmin() {
        return bookDAO.findAllAdmin();
    }
}


