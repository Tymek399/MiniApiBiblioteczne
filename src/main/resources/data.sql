
INSERT INTO users (username, password, role, email) VALUES
('admin', '$2a$10$wY4e6Ap.OdvZ0L/urklJCuPClTKQkj3Hq///U7L0Jcu1.0Y9keX5O', 'ADMIN', 'admin@lib.pl'),
('user', '$2a$10$7S4exODfqX/EZJ.v0N0fWuWhOnH7IkDh9OlzTp6GTqPYaULnTNcZq', 'USER', 'user@example.com');


INSERT INTO books (title, author, isbn, publication_year, barcode) VALUES
('1984', 'George Orwell', '9780451524935', 1949, 'BC1984001'),
('Brave New World', 'Aldous Huxley', '9780060850524', 1932, 'BC1932001'),
('To Kill a Mockingbird', 'Harper Lee', '9780061120084', 1960, 'BC1960001'),
('The Great Gatsby', 'F. Scott Fitzgerald', '9780743273565', 1925, 'BC1925001'),
('The Catcher in the Rye', 'J.D. Salinger', '9780316769488', 1951, 'BC1951001'),
('Fahrenheit 451', 'Ray Bradbury', '9781451673319', 1953, 'BC1953001'),
('Animal Farm', 'George Orwell', '9780451526342', 1945, 'BC1945001'),
('Moby-Dick', 'Herman Melville', '9781503280786', 1851, 'BC1851001');

INSERT INTO borrowings (book_id, user_id, borrow_date, due_date, return_date) VALUES
(1, 2, '2025-05-01', '2025-05-15', NULL),
(5, 2, '2025-04-15', '2025-04-30', '2025-04-29'),
(7, 2, '2025-05-05', '2025-05-19', NULL);
