package com.example.miniapibiblioteczne.mapper;

import com.example.miniapibiblioteczne.dto.BookDto;
import com.example.miniapibiblioteczne.encje.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDto toDto(Book book);
    Book toEntity(BookDto dto);
}

