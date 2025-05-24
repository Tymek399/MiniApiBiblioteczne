package com.example.miniapibiblioteczne.dto;

import com.example.miniapibiblioteczne.encje.User;
import com.example.miniapibiblioteczne.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotBlank(message = "Username cannot be empty")
    @Size(min = 3, max = 50, message = "Username should have at least 3 characters and maximum of 50")
    private String userName;
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, message = "Password should have  at least 8 characters")
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Incorrect email format")
    private String email;

    public static UserDto fromEntity(User user) {
        UserDto dto = new UserDto();
        dto.setUserName(user.getUserName());
        dto.setEmail(user.getEmail());
        dto.setRole(Role.valueOf(user.getRole().name()));
        return dto;
    }

}
