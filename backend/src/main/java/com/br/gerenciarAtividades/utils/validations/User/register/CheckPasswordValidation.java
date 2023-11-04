package com.br.gerenciarAtividades.utils.validations.User.register;

import com.br.gerenciarAtividades.domain.Dtos.user.RegistrarUserDTO;
import com.br.gerenciarAtividades.domain.Dtos.user.UserDtoRequest;
import com.br.gerenciarAtividades.utils.exceptions.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class CheckPasswordValidation implements RegisterValidation{
    @Override
    public void validar(UserDtoRequest dto) {
        if (!dto.password().equals(dto.confirmPassword())){
            throw new ValidationException("As senhas informadas são diferentes");
        }
    }
}
