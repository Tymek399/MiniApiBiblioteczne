package com.example.miniapibiblioteczne.encje;

import com.example.miniapibiblioteczne.enums.Role;
import jakarta.persistence.*;
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
    @Column(unique = true, name = "username")
    private String userName;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String email;

    private boolean active = true;
}
