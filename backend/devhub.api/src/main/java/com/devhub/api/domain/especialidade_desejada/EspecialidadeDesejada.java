package com.devhub.api.domain.especialidade_desejada;

import com.devhub.api.domain.publicacao.Publicacao;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "especialidade_desejada")
@Getter
@Setter
public class EspecialidadeDesejada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeEspecialidade;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "fk_publicacao")
    private Publicacao publicacao;
}
