package com.devhub.api.service.freelancer.dto;

import com.devhub.api.domain.freelancer.Freelancer;

public class FreelancerMapper {
    public static Freelancer of(CreateFreelancerData createFreelancerData) {
        Freelancer freelancer = new Freelancer();

        freelancer.setNome(createFreelancerData.nome());
        freelancer.setCpf(createFreelancerData.cpf());
        freelancer.setEmail(createFreelancerData.email());
        freelancer.setTelefone(createFreelancerData.telefone());
        freelancer.setFuncao(createFreelancerData.funcao());
        freelancer.setValorHora(createFreelancerData.valorHora());
        freelancer.setContratacoes(0);
        freelancer.setAtivo(true);

        return freelancer;
    }
}
