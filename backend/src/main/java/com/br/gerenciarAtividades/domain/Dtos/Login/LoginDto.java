package com.br.gerenciarAtividades.domain.Dtos.Login;

import jakarta.validation.constraints.NotBlank;

public record LoginDto(@NotBlank String login, @NotBlank String senha) {

}
