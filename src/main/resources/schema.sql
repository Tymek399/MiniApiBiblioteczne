DROP TABLE IF EXISTS borrowings;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(255) UNIQUE,
                       password VARCHAR(255),
                       role VARCHAR(50),
                       email VARCHAR(255),
                       active BOOLEAN DEFAULT TRUE
);

CREATE TABLE books (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(255),
                       author VARCHAR(255),
                       isbn VARCHAR(255) UNIQUE,
                       publication_year INT,
                       barcode VARCHAR(255) UNIQUE
);

CREATE TABLE borrowings (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            book_id BIGINT,
                            user_id BIGINT,
                            borrow_date DATE,
                            due_date DATE,
                            return_date DATE,
                            CONSTRAINT fk_book FOREIGN KEY (book_id) REFERENCES books(id),
                            CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);


