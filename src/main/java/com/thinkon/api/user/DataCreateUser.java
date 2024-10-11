package com.thinkon.api.user;

import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.*;

/*
Lançado oficialmente no Java 16, mas disponível desde o Java 14 de maneira experimental,
o Record é um recurso que permite representar uma classe imutável, contendo apenas atributos,
construtor e métodos de leitura, de uma maneira muito simples e enxuta.
 */
public record DataCreateUser(
        @NotNull
        @NotBlank
        String username,
        @NotNull
        @NotBlank
        String firstName,
        @NotNull
        @NotBlank
        String lastName,
        @NotNull
        @Email
        String email,
        @NotNull
        @NotBlank
        //@Pattern(regexp = "^\\+1\\s?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$")
        String phoneNumber
){}
