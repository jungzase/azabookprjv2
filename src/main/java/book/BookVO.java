package book;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookVO {
	private int id;
	private String productName;
	private String isbn;
	private String author;
	private String publisher;
	private Date publicationDate;
	private int price;
	private String category;
	private String imgLink;
}
