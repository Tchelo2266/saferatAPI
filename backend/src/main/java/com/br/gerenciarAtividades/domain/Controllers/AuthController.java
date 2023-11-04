package com.br.gerenciarAtividades.domain.Controllers;


import com.br.gerenciarAtividades.domain.Dtos.Login.LoginDto;
import com.br.gerenciarAtividades.domain.Dtos.Login.LoginResponseDTO;
import com.br.gerenciarAtividades.domain.Models.User;
import com.br.gerenciarAtividades.utils.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid LoginDto request){
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.login(), request.senha());
        System.out.println(usernamePassword.getPrincipal());
        System.out.println(request.senha());
//        var auth = this.authenticationManager.authenticate(usernamePassword);

        Authentication auth;
        try {
            auth = this.authenticationManager.authenticate(usernamePassword);
        } catch (AuthenticationException e) {
            // Registre a exceção para depuração.
            e.printStackTrace();
            // Ou lance a exceção novamente, se necessário.
            throw e;
        }


        var token = tokenService.generateToken((User) auth.getPrincipal());



        return ResponseEntity.ok(new LoginResponseDTO(token));

    }
}
