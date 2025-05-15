DROP TABLE IF EXISTS borrowings;
DROP TABLE IF EXISTS book_copies;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(255) UNIQUE,
                       password VARCHAR(255),
                       role VARCHAR(50),
                       email VARCHAR(255)
);

CREATE TABLE books (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(255),
                       author VARCHAR(255),
                       isbn VARCHAR(255) UNIQUE,
                       publication_year INT
);

CREATE TABLE book_copies (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             book_id BIGINT,
                             title VARCHAR(255),
                             author VARCHAR(255),
                             status VARCHAR(20),
                             CONSTRAINT fk_book FOREIGN KEY (book_id) REFERENCES books(id)
);

CREATE TABLE borrowings (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            book_copy_id BIGINT,
                            user_id BIGINT,
                            borrow_date DATE,
                            due_date DATE,
                            return_date DATE,
                            CONSTRAINT fk_copy FOREIGN KEY (book_copy_id) REFERENCES book_copies(id),
                            CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);
