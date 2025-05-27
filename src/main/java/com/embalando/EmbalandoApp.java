package com.embalando;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 🔹 Marca o ponto de entrada do Spring Boot
public class EmbalandoApp {

    public static void main(String[] args) {
        SpringApplication.run(EmbalandoApp.class, args);
        System.out.println("✅ Sistema Embalando iniciado com sucesso via Spring Boot.");
    }
}
