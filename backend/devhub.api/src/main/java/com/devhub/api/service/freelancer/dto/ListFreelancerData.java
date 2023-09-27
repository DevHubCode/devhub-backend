package com.devhub.api.service.freelancer.dto;

import com.devhub.api.domain.especialidade.Especialidade;
import com.devhub.api.domain.freelancer.Freelancer;
import com.devhub.api.domain.funcao.Funcao;

import java.util.List;

public record ListFreelancerData(Long id_freelancer, String nome, String telefone, String email, Integer contratacoes, Funcao funcao, List<Especialidade> especialidades, Double valorHora, String descricao, Boolean ativo) {
    public ListFreelancerData(Freelancer freelancer) {
        this(freelancer.getId_freelancer(), freelancer.getNome(), freelancer.getTelefone(), freelancer.getEmail(), freelancer.getContratacoes(),
            freelancer.getFuncao(), freelancer.getEspecialidades(), freelancer.getValorHora(),freelancer.getDescricao(),freelancer.getAtivo());
    }
}
