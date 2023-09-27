package com.devhub.api.service.freelancer.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateFreelancerData(@NotNull Long id_freelancer ,String nome, String telefone, String senha, String descricao, Double valorHora) {

}
