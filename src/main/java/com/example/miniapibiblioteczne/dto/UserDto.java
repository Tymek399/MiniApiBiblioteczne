package com.example.miniapibiblioteczne.dto;

import com.example.miniapibiblioteczne.encje.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String userName;
    private String email;
    private String password;
    private String role;



    public static UserDto fromEntity(User user) {
        UserDto dto = new UserDto();
        dto.setUserName(user.getUserName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole().name());
        return dto;
    }
}
