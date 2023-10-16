package com.devhub.api.domain.publicacao;

import com.devhub.api.domain.contratante.Contratante;
import com.devhub.api.domain.especialidade_desejada.EspecialidadeDesejada;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "publicacao")
@Getter
@Setter
@NoArgsConstructor
public class Publicacao {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "fk_contratante")
    private Contratante contratante;
    private LocalDateTime createdAt;

    @JsonManagedReference
    @OneToMany(mappedBy = "publicacao")
    private List<EspecialidadeDesejada> especialidadeDesejadas;

    public Publicacao(CreatePublicacaoData data, Contratante contratante) {
        this.titulo = data.titulo();
        this.descricao = data.descricao();
        this.contratante = contratante;
        this.createdAt = data.createdAt();
    }
}
