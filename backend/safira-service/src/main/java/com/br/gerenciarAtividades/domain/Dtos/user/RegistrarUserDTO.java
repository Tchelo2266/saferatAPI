package com.br.gerenciarAtividades.domain.Dtos.user;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegistrarUserDTO(
        @NotBlank String login, // Deixar esse campo como único
        @Email(message = "Insira um email válido, por favor") @NotBlank String email,
        @NotBlank String password,
        @NotBlank String confirmPassword ) {
}
