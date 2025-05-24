package com.example.miniapibiblioteczne.encje;

import jakarta.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tytuł nie może być pusty")
    @Size(min = 2, message = "Tytuł musi mieć co najmniej 2 znaki")
    private String title;

    @NotBlank(message = "Autor nie może być pusty")
    private String author;

    @NotBlank(message = "ISBN nie może być pusty")
    @Pattern(regexp = "^(97(8|9))?\\d{9}(\\d|X)$", message = "Nieprawidłowy format ISBN")
    private String isbn;

    @Min(value = 1450, message = "Rok publikacji musi być większy lub równy 1450")
    @Column(name = "publication_year")
    private Integer publicationYear;

    @Column(unique = true)
    @NotBlank(message = "Kod kreskowy (barcode) nie może być pusty")
    private String barcode;
}
