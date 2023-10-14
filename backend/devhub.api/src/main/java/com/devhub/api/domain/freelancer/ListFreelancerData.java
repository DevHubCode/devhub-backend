package com.devhub.api.domain.freelancer;

import com.devhub.api.domain.especialidade.Especialidade;
import com.devhub.api.domain.funcao.Funcao;

import java.util.List;

public record ListFreelancerData(Long id_freelancer, String nome, String telefone, String email, Integer contratacoes,
                                 Funcao funcao, List<Especialidade> especialidades, Double valorHora, String descricao,
                                 Boolean ativo) {
    public ListFreelancerData(Freelancer freelancer) {
        this(freelancer.getId(), freelancer.getNome(), freelancer.getTelefone(), freelancer.getEmail(), freelancer.getContratacoes(),
                freelancer.getFuncao(), freelancer.getEspecialidades(), freelancer.getValorHora(), freelancer.getDescricao(), freelancer.getAtivo());
    }
}
