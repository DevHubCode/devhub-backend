package com.devhub.api.domain.contratante;

import jakarta.validation.constraints.NotNull;

public record UpdateContratanteData(@NotNull Long id, String nome, String telefone, String senha) {

}

