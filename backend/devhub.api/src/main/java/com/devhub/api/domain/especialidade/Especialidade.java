package com.devhub.api.domain.especialidade;

import com.devhub.api.domain.freelancer.Freelancer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "especialidades")
@Entity(name = "Especialidade")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_especialidade")
public class Especialidade {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
