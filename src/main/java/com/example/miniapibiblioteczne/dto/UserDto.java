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
    @NotBlank(message = "Nazwa użytkownika nie może być pusta")
    @Size(min = 3, max = 50, message = "Nazwa użytkownika musi mieć od 3 do 50 znaków")
    private String userName;

    @NotBlank(message = "Hasło nie może być puste")
    @Size(min = 8, message = "Hasło musi mieć co najmniej 8 znaków")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @NotBlank(message = "Email nie może być pusty")
    @Email(message = "Nieprawidłowy format emaila")
    private String email;



    public static UserDto fromEntity(User user) {
        UserDto dto = new UserDto();
        dto.setUserName(user.getUserName());
        dto.setEmail(user.getEmail());
        dto.setRole(Role.valueOf(user.getRole().name()));
        return dto;
    }

}
