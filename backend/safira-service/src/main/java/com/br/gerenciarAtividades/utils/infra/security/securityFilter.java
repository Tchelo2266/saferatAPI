package com.br.gerenciarAtividades.utils.infra.security;

import com.br.gerenciarAtividades.domain.Repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component // componente generico
public class securityFilter extends OncePerRequestFilter { // implementa a interface filter pelo Spring

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, ServletException {

        var tokenJWT = recuperarToken(request);

        if(tokenJWT != null){
            var subject = tokenService.getSubject(tokenJWT);
            var usuario = usuarioRepository.findByLogin(subject);

            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("LOGADO NA REQUISICAO");
        }

        // segue o fluxo da requisicao
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null){
            return authorizationHeader.replace("Bearer ", "").trim();
        }

        return null;


    }
}
