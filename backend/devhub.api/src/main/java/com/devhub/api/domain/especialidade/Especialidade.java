package com.devhub.api.domain.especialidade;

import com.devhub.api.domain.freelancer.Freelancer;
import com.devhub.api.service.especialidade.dto.EspecialidadeData;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@Table(name = "especialidades")
@Entity(name = "Especialidade")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_especialidade")
public class Especialidade {
    @JsonIgnore
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_especialidade;

    private String descricao;
    private String tempoExp;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "fk_freelancer")
    private Freelancer freelancer;

    public Especialidade(EspecialidadeData data, Freelancer freelancer) {
        this.descricao = data.descricao();
        this.tempoExp = data.tempoExp();
        this.freelancer = freelancer;
    }
}
