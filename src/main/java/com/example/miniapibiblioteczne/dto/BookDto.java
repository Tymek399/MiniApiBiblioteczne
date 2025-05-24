package com.example.miniapibiblioteczne.dto;

import com.example.miniapibiblioteczne.encje.Book;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    @NotBlank(message = "Tytuł nie może być pusty")
    @Size(min = 2, message = "Tytuł musi mieć co najmniej 2 znaki")
    private String title;

    @NotBlank(message = "Autor nie może być pusty")
    private String author;

    @NotBlank(message = "ISBN nie może być pusty")
    @Pattern(regexp = "^(97(8|9))?\\d{9}(\\d|X)$", message = "Nieprawidłowy format ISBN")
    private String isbn;

    @Min(value = 1450, message = "Rok publikacji musi być większy lub równy 1450")
    private Integer publicationYear;


    @NotBlank(message = "Kod kreskowy nie może być pusty")
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
