package com.example.miniapibiblioteczne;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MiniApiBiblioteczneApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(MiniApiBiblioteczneApplication.class, args);
        context.getApplicationStartup();
    }

}
