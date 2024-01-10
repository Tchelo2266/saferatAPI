package com.br.gerenciarAtividades.utils.validations.User.register;

import com.br.gerenciarAtividades.domain.Dtos.user.RegistrarUserDTO;
import com.br.gerenciarAtividades.domain.Dtos.user.UserDtoRequest;

public interface RegisterValidation {
    void validar(UserDtoRequest dto);
}
