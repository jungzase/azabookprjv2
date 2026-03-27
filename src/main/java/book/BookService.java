package book;

import java.util.List;

public interface BookService {
    List<BookVO> getBookList(String keyword, Long categoryId);
    List<BookVO> getNewestBooks();
    List<BookVO> getTopRankedBooks(int limit);
    BookVO getBook(String isbn);
    boolean insert(BookVO vo);
    boolean update(BookVO vo);
    boolean delete(String isbn);
    List<BookVO> getAllBooksAdmin();
}


