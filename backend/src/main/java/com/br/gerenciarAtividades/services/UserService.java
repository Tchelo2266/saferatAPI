package com.br.gerenciarAtividades.services;

import com.br.gerenciarAtividades.domain.Dtos.user.RegistrarUserDTO;
import com.br.gerenciarAtividades.domain.Dtos.user.UpdateUserDTO;
import com.br.gerenciarAtividades.domain.Dtos.user.UserDtoRequest;
import com.br.gerenciarAtividades.domain.Dtos.user.UserResponseDTO;
import com.br.gerenciarAtividades.domain.Models.User;
import com.br.gerenciarAtividades.domain.Repositories.UserRepository;
import com.br.gerenciarAtividades.utils.exceptions.ValidationException;
import com.br.gerenciarAtividades.utils.validations.User.register.RegisterValidation;
import com.br.gerenciarAtividades.utils.validations.User.register.UniqueEmailValidation;
import com.br.gerenciarAtividades.utils.validations.User.register.UniqueLoginValidation;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private List<RegisterValidation> validadores;

    @Autowired
    private UniqueEmailValidation uniqueEmailValidation;

    @Autowired
    private UniqueLoginValidation uniqueLoginValidation;

    @Autowired
    PasswordEncoder encoder;

    public UserResponseDTO registerUser(RegistrarUserDTO dto){
        var dtoValidation = new UserDtoRequest(dto.login(), dto.email(), dto.password(), dto.confirmPassword());
        validadores.forEach(v -> v.validar(dtoValidation));
        var user = new User(dto);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return new UserResponseDTO(user.getLogin(), user.getEmail());
    }

    public User updateUser(UpdateUserDTO dto, String login) {

        var user = userRepository.findUserByLogin(login);
        if(user == null){
            throw new EntityNotFoundException("Usuário não encontrado no sistema");
        }

        var dtoValidation = new UserDtoRequest(dto.login(), dto.email(), "", "");

        // Validando novos dados
        if(dto.login().isBlank() && dto.email().isBlank())
            throw new ValidationException("Os campos inseridos estão vazios");

        if (!dto.login().isBlank())
            uniqueLoginValidation.validar(dtoValidation);

        if (!dto.email().isBlank())
            uniqueEmailValidation.validar(dtoValidation);

        user.setEmail(dto.email());
        user.setLogin(dto.login());

        userRepository.save(user);

        return user;


    }

    public UserResponseDTO deleteUser(String login) {
        var user = userRepository.findUserByLogin(login);
        if (user == null) {
            throw new EntityNotFoundException("Usuário não encontrado no sistema");
        }

        userRepository.delete(user);
        return new UserResponseDTO(user.getLogin(), user.getEmail());
    }
}
