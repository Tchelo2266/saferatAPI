package com.br.gerenciarAtividades.domain.Models;

import com.br.gerenciarAtividades.domain.Dtos.atividades.CadastrarAtividadeDTO;
import com.br.gerenciarAtividades.domain.Dtos.user.UserResponseDTO;
import com.br.gerenciarAtividades.utils.enums.Prioridade;
import com.br.gerenciarAtividades.utils.enums.Tipo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Document(collection = "Activities")
public class Atividades {

    @Id
    private String id;

    private String login;

    private String content;

    @Enumerated
    private Prioridade prioridade;

    @Enumerated
    private Tipo tipo;

    private LocalDateTime dueDate;

    private boolean done;

    public Atividades(CadastrarAtividadeDTO dto) {
        this.login = dto.login();
        this.content = dto.content();
        this.prioridade = dto.prioridade();
        this.tipo = dto.tipo();
        this.dueDate = dto.dueDate();
        this.done = false;

    }
}
