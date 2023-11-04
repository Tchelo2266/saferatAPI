package com.br.gerenciarAtividades.domain.Controllers;

import com.br.gerenciarAtividades.domain.Dtos.user.RegistrarUserDTO;
import com.br.gerenciarAtividades.domain.Dtos.user.UpdateUserDTO;
import com.br.gerenciarAtividades.domain.Dtos.user.UserResponseDTO;
import com.br.gerenciarAtividades.domain.Models.User;
import com.br.gerenciarAtividades.domain.Repositories.UserRepository;
import com.br.gerenciarAtividades.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody @Valid RegistrarUserDTO dto){
        var user = userService.registerUser(dto);
        return ResponseEntity.ok(user);

    }

    @GetMapping(value = "/getUsers")
    public List<UserResponseDTO> getBooksBooks(){
        System.out.println("Get Users");
        List<User> users =  userRepository.findAll();
        return users.stream().map(v -> new UserResponseDTO(v.getLogin(), v.getEmail())).toList();

    }

    @GetMapping(value = "/{login}")
    public UserResponseDTO getUserByLogin(@PathVariable String login){
        var user = userRepository.findUserByLogin(login);
        if(user == null)
            throw new EntityNotFoundException("Não foi possivel encontrar esse usuario");
        return new UserResponseDTO(user.getLogin(), user.getEmail());
    }

    @PutMapping(value = "/updateUser/{login}")
    public UserResponseDTO updateUser(@RequestBody @Valid UpdateUserDTO dto, @PathVariable String login){
        var user = userService.updateUser(dto, login);
        return new UserResponseDTO(user.getLogin(), user.getEmail());
    }

    @DeleteMapping(value = "/deleteUser/{login}")
    public UserResponseDTO deleteUser(@PathVariable String login){
        return userService.deleteUser(login);
    }

}
