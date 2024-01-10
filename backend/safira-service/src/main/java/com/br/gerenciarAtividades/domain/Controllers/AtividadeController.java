package com.br.gerenciarAtividades.domain.Controllers;

import com.br.gerenciarAtividades.domain.Dtos.atividades.CadastrarAtividadeDTO;
import com.br.gerenciarAtividades.domain.Models.Atividades;
import com.br.gerenciarAtividades.domain.Repositories.AtividadeRepository;
import com.br.gerenciarAtividades.domain.Repositories.UserRepository;
import com.br.gerenciarAtividades.services.AtividadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/atividades")
public class AtividadeController {

    @Autowired
    AtividadeService atividadeService;

    @Autowired
    AtividadeRepository atividadeRepository;

    @Autowired
    UserRepository userRepository;
    @PostMapping
    public ResponseEntity<Atividades> cadastrarAtividade(@RequestBody @Valid CadastrarAtividadeDTO dto){
        var atividade = atividadeService.createActivity(dto);
        return ResponseEntity.ok(atividade);
    }

    @GetMapping(value = "/{login}")
    public List<Atividades> getActivitiesByLogin(@PathVariable String login){
        List<Atividades> atividades = atividadeRepository.findAllByLogin(login);
        return atividades.stream().filter(a -> !a.isDone()).collect(Collectors.toList());
    }

    @PostMapping(value = "/check/{id}")
    public ResponseEntity<Atividades> doneActitity(@PathVariable String id){
        var atividade = atividadeService.doneActivity(id);
        return ResponseEntity.ok(atividade);
    }
}
