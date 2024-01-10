package com.br.gerenciarAtividades.domain.Repositories;

import com.br.gerenciarAtividades.domain.Models.Atividades;
import com.br.gerenciarAtividades.domain.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AtividadeRepository extends MongoRepository<Atividades, String> {
    List<Atividades> findAllByLogin(String login);
}
