package com.example.miniapibiblioteczne.dto;

import com.example.miniapibiblioteczne.encje.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private String title;
    private String author;
    private String isbn;
    private Integer publicationYear;
    private String barcode;

    public static BookDto fromEntity(Book book) {
        if (book == null) return null;

        return new BookDto(
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getPublicationYear(),
                book.getBarcode()
        );
    }

    public static Book toEntity(BookDto dto) {
        if (dto == null) return null;

        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setPublicationYear(dto.getPublicationYear());
        book.setBarcode(dto.getBarcode());

        return book;
    }


}
