DROP TABLE IF EXISTS users;

CREATE TABLE users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(30) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    hp VARCHAR(20),
    address VARCHAR(255),
    role VARCHAR(20) NOT NULL DEFAULT 'USER',
    reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO users (username, password, name, email, hp, address, role)
VALUES ('admin', '1234', 'Admin', 'admin@azabook.com', '010-1111-1111', 'Seoul', 'ADMIN');

INSERT INTO users (username, password, name, email, hp, address, role)
VALUES ('user1', '1234', 'User One', 'user1@azabook.com', '010-2222-2222', 'Busan', 'USER');

