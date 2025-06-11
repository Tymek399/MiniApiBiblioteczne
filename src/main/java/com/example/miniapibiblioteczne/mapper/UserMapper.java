package com.example.miniapibiblioteczne.mapper;

import com.example.miniapibiblioteczne.dto.UserDto;
import com.example.miniapibiblioteczne.encje.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

//tu masz mapStruck i w bookMapper

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    @Mapping(target = "id", ignore = true)//ignoruje id bo w userdto i bookdto nie uzywamy id
    User toEntity(UserDto dto);
}
