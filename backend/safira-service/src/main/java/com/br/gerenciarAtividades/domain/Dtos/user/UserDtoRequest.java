package com.br.gerenciarAtividades.domain.Dtos.user;

public record UserDtoRequest (
        String login,
        String email,
        String password,
        String confirmPassword
){
}
