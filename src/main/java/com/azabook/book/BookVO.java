package com.azabook.book;

import java.sql.Date;

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

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public String getBookName() { return bookName; }
    public void setBookName(String bookName) { this.bookName = bookName; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }
    public Date getPublicationDate() { return publicationDate; }
    public void setPublicationDate(Date publicationDate) { this.publicationDate = publicationDate; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    public String getImgLink() { return imgLink; }
    public void setImgLink(String imgLink) { this.imgLink = imgLink; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getRankNum() { return rankNum; }
    public void setRankNum(int rankNum) { this.rankNum = rankNum; }
}