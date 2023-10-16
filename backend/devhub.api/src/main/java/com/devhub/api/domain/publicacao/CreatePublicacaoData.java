package com.devhub.api.domain.publicacao;

import com.devhub.api.domain.especialidade_desejada.EspecialidadeDesejadaData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

public record CreatePublicacaoData(
        @NotBlank
        String titulo,
        @NotBlank
        String descricao,
        @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime createdAt,
        @NotNull
        @Valid
        List<EspecialidadeDesejadaData> especialidadesDesejadas
) {
}
