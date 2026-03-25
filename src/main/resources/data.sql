INSERT INTO users (username, password, name, email, hp, address, role)
VALUES ('admin', '1234', '관리자', 'admin@azabook.com', '010-1111-1111', '서울 강남구', 'ADMIN');

INSERT INTO users (username, password, name, email, hp, address, role)
VALUES ('user1', '1234', '홍길동', 'user1@azabook.com', '010-2222-2222', '서울 송파구', 'USER');

INSERT INTO categories (category_name) VALUES ('소설');
INSERT INTO categories (category_name) VALUES ('IT');
INSERT INTO categories (category_name) VALUES ('자기계발');

INSERT INTO books (isbn, book_name, author, publisher, publication_date, price, stock, category_id, img_link, description, rank_num)
VALUES ('9780000000001', '스프링 입문', '김자바', '아자출판', '2025-01-10', 30000, 10, 2, 'book1.jpg', '스프링 MVC 입문서', 1);

INSERT INTO books (isbn, book_name, author, publisher, publication_date, price, stock, category_id, img_link, description, rank_num)
VALUES ('9780000000002', '자바의 정석', '남궁성', '도우출판', '2024-08-20', 28000, 15, 2, 'book2.jpg', '자바 대표 입문서', 2);

INSERT INTO books (isbn, book_name, author, publisher, publication_date, price, stock, category_id, img_link, description, rank_num)
VALUES ('9780000000003', '오늘의 소설', '이작가', '문학출판', '2024-12-01', 18000, 20, 1, 'book3.jpg', '감성 소설', 3);