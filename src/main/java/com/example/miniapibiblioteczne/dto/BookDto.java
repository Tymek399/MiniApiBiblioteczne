package com.example.miniapibiblioteczne.dto;

import com.example.miniapibiblioteczne.encje.Book;
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
    @NotBlank(message = "Title cannot be empty")
    @Size(min = 2, message = "Title should have at least 2 characters")
    private String title;
    @NotBlank(message = "Author cannot be empty")
    private String author;
    @NotBlank(message = "ISBN  cannot be empty")
    @Pattern(regexp = "^(97(8|9))?\\d{9}(\\d|X)$", message = "Incorrect ISBN format")
    private String isbn;
    @Min(value = 1450, message = "Publication year shouldn't be earlier then 1450")
    private Integer publicationYear;
    @NotBlank(message = "Barcode cannot be empty")
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
