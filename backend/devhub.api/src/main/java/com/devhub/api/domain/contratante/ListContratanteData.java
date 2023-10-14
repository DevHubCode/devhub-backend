package com.devhub.api.domain.contratante;

public record ListContratanteData(Long id, String nome, String cnpj, String telefone, String email,
                                  Integer contratacoes) {
    public ListContratanteData(Contratante contratante) {
        this(contratante.getId(), contratante.getNome(), contratante.getCnpj(), contratante.getTelefone(), contratante.getEmail(), contratante.getContratacoes());
    }
}
