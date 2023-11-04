package com.br.gerenciarAtividades.utils.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.br.gerenciarAtividades.domain.Models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}") // Passa o valor que vc deseja que a variavel receba
    private String secret;

    private static final String ISSUER = "GerenciadorDeTarefas";

    public String generateToken(User usuario){

        try{
            var algoritmo = Algorithm.HMAC256(secret); // insira uma senha secreta da sua API
            String token = JWT.create()
                    .withIssuer(ISSUER) // identificacao da aplicacao
                    .withSubject(usuario.getLogin())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo); // assinatura da senha

//            O token funciona como o session do programa, ele guarda as informações do código e lanca os dados que voce deseja salvar,seja identificador, permissoes, etc

            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token jwt", exception);

        }
    }

    public String getSubject(String tokenJWT){
        try{
            var algoritmo = Algorithm.HMAC256(secret); // insira uma senha secreta da sua API
            return JWT.require(algoritmo)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(tokenJWT) // verificar se o token esta valido de acordo com o issuer passado
                    .getSubject();

        } catch (JWTCreationException exception){
            throw new RuntimeException("Token JWT inválido ou expirado: " + tokenJWT);

        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
