package com.br.gerenciarAtividades.services;

import com.br.gerenciarAtividades.domain.Dtos.atividades.CadastrarAtividadeDTO;
import com.br.gerenciarAtividades.domain.Dtos.user.UserResponseDTO;
import com.br.gerenciarAtividades.domain.Models.Atividades;
import com.br.gerenciarAtividades.domain.Repositories.AtividadeRepository;
import com.br.gerenciarAtividades.domain.Repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtividadeService {

    @Autowired
    AtividadeRepository atividadeRepository;

    @Autowired
    UserRepository userRepository;

    public Atividades createActivity(CadastrarAtividadeDTO dto) {
        var user = userRepository.findByLogin(dto.login());
        if (user == null) {
            throw new EntityNotFoundException("Usuário não encontrado no sistema");
        }

        var atividade = new Atividades(dto);
        atividadeRepository.save(atividade);
        return atividade;
    }


    public Atividades doneActivity(String id) {
        var atividade = atividadeRepository.findById(id);

        if(atividade.isPresent()){
            Atividades atividades = atividade.get();
            atividades.setDone(true);
            return atividadeRepository.save(atividades);
        } else{
            throw new EntityNotFoundException("Atividade não encontrada");
        }
    }
}
