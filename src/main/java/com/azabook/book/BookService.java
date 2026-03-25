package com.azabook.book;

import java.util.List;

public interface BookService {
    List<BookVO> getBookList(String keyword, Long categoryId);
    BookVO getBook(String isbn);
    boolean insert(BookVO vo);
    boolean update(BookVO vo);
    boolean delete(String isbn);
    List<BookVO> getAllBooksAdmin();
}