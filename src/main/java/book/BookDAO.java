package book;

import java.util.List;

public interface BookDAO {
    List<BookVO> findAll(String keyword, Long categoryId);
    List<BookVO> findNewest();
    List<BookVO> findTopRanked(int limit);
    BookVO findByIsbn(String isbn);
    int insert(BookVO vo);
    int update(BookVO vo);
    int delete(String isbn);
    List<BookVO> findAllAdmin();
    int updateStock(String isbn, int stock);
}


