package com.devhub.api.domain.contratante;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CNPJ;


public record CreateContratanteData(
        @NotBlank
        String nome,
        @NotBlank
        @CNPJ
        String cnpj,
        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String telefone,
        @Email
        String email,
        @NotBlank
        String senha
) {
}
