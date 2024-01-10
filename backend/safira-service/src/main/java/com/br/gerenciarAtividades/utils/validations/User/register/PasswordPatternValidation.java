package com.br.gerenciarAtividades.utils.validations.User.register;

import com.br.gerenciarAtividades.domain.Dtos.user.RegistrarUserDTO;
import com.br.gerenciarAtividades.domain.Dtos.user.UserDtoRequest;
import com.br.gerenciarAtividades.utils.exceptions.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class PasswordPatternValidation implements RegisterValidation {
    @Override
    public void validar(UserDtoRequest dto) {
        System.out.println(dto.password() + "\n");

        String senha = dto.password();

        // Comprimento Mínimo = 8 Caracteres
        if(senha.length() < 8)
            throw new ValidationException("A senha inserida possui menos de 8 caracteres");

        // Caracteres Especiais
        if(senha.matches(".*!@#$%¨&*-_.*"))
            throw new ValidationException("A senha inserida não possui nenhuma caractere especial");

        // Letras maíusculas
        if(!senha.matches(".*[A-Z].*"))
            throw new ValidationException("A senha inserida não possui caracteres maiúsculos");

        // Caracteres minúsculos
        if(!senha.matches(".*[a-z].*"))
            throw new ValidationException("A senha inserida não possui caracteres minúsculos");

        // Caracteres numericos
        if(!senha.matches(".*\\d.*"))
            throw new ValidationException(("A senha inserida não possui caracteres numéricos"));
    }
}
