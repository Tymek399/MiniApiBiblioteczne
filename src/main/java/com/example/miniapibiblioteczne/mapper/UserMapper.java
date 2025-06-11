package com.example.miniapibiblioteczne.mapper;

import com.example.miniapibiblioteczne.dto.UserDto;
import com.example.miniapibiblioteczne.encje.User;
import org.mapstruct.Mapper;

//tu masz mapStruck i w bookMapper

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto dto);
}
