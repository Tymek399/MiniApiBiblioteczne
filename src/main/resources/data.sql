-- Użytkownicy
INSERT INTO users (username, password, role, email) VALUES
('admin', '{noop}admin123', 'ADMIN', 'admin@lib.pl'),
('user', '{noop}user123', 'USER', 'user@example.com');

-- Książki
INSERT INTO books (title, author, isbn, publication_year) VALUES
('1984', 'George Orwell', '9780451524935', 1949),
('Brave New World', 'Aldous Huxley', '9780060850524', 1932),
('To Kill a Mockingbird', 'Harper Lee', '9780061120084', 1960),
('The Great Gatsby', 'F. Scott Fitzgerald', '9780743273565', 1925),
('The Catcher in the Rye', 'J.D. Salinger', '9780316769488', 1951),
('Fahrenheit 451', 'Ray Bradbury', '9781451673319', 1953),
('Animal Farm', 'George Orwell', '9780451526342', 1945),
('Moby-Dick', 'Herman Melville', '9781503280786', 1851);

-- Kopie książek
INSERT INTO book_copies (book_id, status) VALUES
(1, 'AVAILABLE'),
(1, 'BORROWED'),
(2, 'AVAILABLE'),
(3, 'AVAILABLE'),
(4, 'AVAILABLE'),
(4, 'AVAILABLE'),
(5, 'BORROWED'),
(6, 'AVAILABLE'),
(7, 'AVAILABLE'),
(7, 'BORROWED'),
(8, 'AVAILABLE');

-- Wypożyczenia
INSERT INTO borrowings (book_copy_id, user_id, borrow_date, due_date, return_date) VALUES
(2, 2, '2025-05-01', '2025-05-15', NULL),
(7, 2, '2025-04-15', '2025-04-30', '2025-04-29'),
(10, 2, '2025-05-05', '2025-05-19', NULL);
