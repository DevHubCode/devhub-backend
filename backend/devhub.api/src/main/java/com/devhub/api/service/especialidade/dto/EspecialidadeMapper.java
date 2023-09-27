package com.devhub.api.service.especialidade.dto;

import com.devhub.api.domain.especialidade.Especialidade;
import com.devhub.api.domain.freelancer.Freelancer;

public class EspecialidadeMapper {
    public static Especialidade of(EspecialidadeData especialidadeData, Freelancer freelancer) {
        Especialidade especialidade = new Especialidade();

        especialidade.setDescricao(especialidadeData.descricao());
        especialidade.setTempoExp(especialidadeData.tempoExp());
        especialidade.setFreelancer(freelancer);

        return especialidade;
    }
}
