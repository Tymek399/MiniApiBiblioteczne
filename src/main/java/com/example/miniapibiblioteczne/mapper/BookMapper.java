package com.example.miniapibiblioteczne.mapper;

import com.example.miniapibiblioteczne.dto.BookDto;
import com.example.miniapibiblioteczne.encje.Book;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {


    BookDto toDto(Book book);

    @Mapping(target = "id", ignore = true)
    Book toEntity(BookDto dto);
}



