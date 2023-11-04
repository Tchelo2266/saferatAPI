package com.br.gerenciarAtividades.utils.validations.User.register;

import com.br.gerenciarAtividades.domain.Dtos.user.RegistrarUserDTO;
import com.br.gerenciarAtividades.domain.Dtos.user.UserDtoRequest;
import com.br.gerenciarAtividades.domain.Repositories.UserRepository;
import com.br.gerenciarAtividades.utils.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueLoginValidation implements RegisterValidation{

    @Autowired
    UserRepository userRepository;
    @Override
    public void validar(UserDtoRequest dto) {
        var user = userRepository.findByLogin(dto.login());

        if(user != null){
            throw new ValidationException("Login já cadastrado no sistema");
        }

    }
}
