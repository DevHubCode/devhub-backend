package com.devhub.api.domain.contratante;

public record DetailContratanteData(Long id, String nome, String cnpj, String telefone, String email,
                                    Integer contratacoes, Boolean ativo) {

    public DetailContratanteData(Contratante contratante) {
        this(
                contratante.getId(),
                contratante.getNome(),
                contratante.getCnpj(),
                contratante.getTelefone(),
                contratante.getEmail(),
                contratante.getContratacoes(),
                contratante.getAtivo());
    }
}
