package com.devhub.api.domain.usuario;

import com.devhub.api.domain.contratante.UpdateContratanteData;
import com.devhub.api.domain.freelancer.UpdateFreelancerData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@EqualsAndHashCode(of = "id")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String nome;

    protected String telefone;

    protected String email;

    protected String senha;

    protected int contratacoes;

    protected Boolean ativo;

    public Usuario(String nome, String telefone, String email, String senha) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.contratacoes = 0;
        this.ativo = true;
    }

}