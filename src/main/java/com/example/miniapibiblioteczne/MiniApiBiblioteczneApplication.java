package com.example.miniapibiblioteczne;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MiniApiBiblioteczneApplication {

    public static void main(String[] args) {
      SpringApplication.run(MiniApiBiblioteczneApplication.class, args);

    }

}
