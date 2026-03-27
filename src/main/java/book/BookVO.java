package book;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookVO {
    private String isbn;
    private String bookName;
    private String author;
    private String publisher;
    private Date publicationDate;
    private int price;
    private int stock;
    private Long categoryId;
    private String categoryName;
    private String imgLink;
    private String description;
    private int rankNum;

    
}

