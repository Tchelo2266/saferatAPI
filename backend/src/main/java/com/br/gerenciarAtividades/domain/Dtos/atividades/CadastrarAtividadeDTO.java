package com.br.gerenciarAtividades.domain.Dtos.atividades;

import com.br.gerenciarAtividades.utils.enums.Prioridade;
import com.br.gerenciarAtividades.utils.enums.Tipo;

import java.time.LocalDateTime;

public record CadastrarAtividadeDTO(String login, String content, Prioridade prioridade, Tipo tipo, LocalDateTime dueDate) {
}
