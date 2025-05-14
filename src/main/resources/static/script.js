const baseUrl = 'http://localhost:8080/api/books';

// Pobieranie książek
document.getElementById('get-books-btn').addEventListener('click', async () => {
    const response = await fetch(baseUrl);
    const books = await response.json();
    displayBooks(books);
});

// Wyszukiwanie książek
document.getElementById('search-btn').addEventListener('click', async () => {
    const title = document.getElementById('search-title').value;
    const author = document.getElementById('search-author').value;

    let searchParams = '';
    if (title) searchParams += `title=${title}&`;
    if (author) searchParams += `author=${author}&`;

    const response = await fetch(`${baseUrl}/search?${searchParams}`);
    const books = await response.json();
    displayBooks(books);
});

// Dodawanie książki
document.getElementById('add-book-btn').addEventListener('click', async () => {
    const title = document.getElementById('add-title').value;
    const author = document.getElementById('add-author').value;
    const publisher = document.getElementById('add-publisher').value;
    const year = document.getElementById('add-year').value;

    const book = { title, author, publisher, year };

    const response = await fetch(baseUrl, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(book)
    });

    const newBook = await response.json();
    alert('Book added successfully!');
    document.getElementById('add-book-form').reset();
});

// Edytowanie książki
document.getElementById('update-book-btn').addEventListener('click', async () => {
    const id = document.getElementById('update-id').value;
    const title = document.getElementById('update-title').value;
    const author = document.getElementById('update-author').value;
    const publisher = document.getElementById('update-publisher').value;
    const year = document.getElementById('update-year').value;

    const book = { title, author, publisher, year };

    const response = await fetch(`${baseUrl}/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(book)
    });

    const updatedBook = await response.json();
    alert('Book updated successfully!');
});

// Usuwanie książki
async function deleteBook(id) {
    await fetch(`${baseUrl}/${id}`, { method: 'DELETE' });
    alert('Book deleted successfully!');
    document.getElementById('get-books-btn').click(); // Refresh the book list
}

// Wyświetlanie książek
function displayBooks(books) {
    const booksList = document.getElementById('books');
    booksList.innerHTML = ''; // Clear the list first

    books.forEach(book => {
        const li = document.createElement('li');
        li.innerHTML = `
            ${book.title} by ${book.author} (${book.year})
            <button onclick="deleteBook(${book.id})">Delete</button>
            <button onclick="editBook(${book.id})">Edit</button>
        `;
        booksList.appendChild(li);
    });
}

// Edytowanie książki (wypełnienie formularza)
function editBook(id) {
    const book = document.querySelector(`#book-${id}`);
    document.getElementById('update-id').value = id;
    document.getElementById('update-title').value = book.title;
    document.getElementById('update-author').value = book.author;
    document.getElementById('update-publisher').value = book.publisher;
    document.getElementById('update-year').value = book.year;
}
