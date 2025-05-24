package com.example.miniapibiblioteczne.encje;

import com.example.miniapibiblioteczne.enums.Role;
import jakarta.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nazwa użytkownika nie może być pusta")
    @Size(min = 3, max = 50, message = "Nazwa użytkownika musi mieć od 3 do 50 znaków")
    @Column(unique = true, name = "username")
    private String userName;

    @NotBlank(message = "Hasło nie może być puste")
    @Size(min = 8, message = "Hasło musi mieć co najmniej 8 znaków")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @NotBlank(message = "Email nie może być pusty")
    @Email(message = "Nieprawidłowy format emaila")
    private String email;


}
