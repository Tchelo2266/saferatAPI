package com.br.gerenciarAtividades.domain.Repositories;

import com.br.gerenciarAtividades.domain.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends MongoRepository<User, String> {
    UserDetails findByLogin(String login);

    @Query("{ 'login' : ?0 }")
    User findUserByLogin(String login);

    User findByEmail(String email);
}
