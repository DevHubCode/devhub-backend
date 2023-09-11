package com.devhub.api.domain.freelancer;

import com.devhub.api.domain.especialidade.Especialidade;
import com.devhub.api.domain.funcao.Funcao;

import java.util.List;

public record DetailFreelancerData(Long id_freelancer, String nome, String telefone, String email, Integer contratacoes, Funcao funcao, Double valorHora, String descricao, Boolean ativo) {
    public DetailFreelancerData(Freelancer freelancer){
        this(freelancer.getId_freelancer(),
             freelancer.getNome(),
             freelancer.getTelefone(),
             freelancer.getEmail(),
             freelancer.getContratacoes(),
             freelancer.getFuncao(),
             freelancer.getValorHora(),
             freelancer.getDescricao(),
             freelancer.getAtivo());
    }
}
